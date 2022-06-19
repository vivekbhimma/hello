package com.hello.infrastructure.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.infrastructure.adapter.dto.DemandRentDto;
import com.hello.infrastructure.adapter.dto.GrowthDto;
import com.hello.application.service.exception.InsufficientDataException;
import com.hello.application.service.exception.InvalidPostalCodeException;
import com.hello.application.service.exception.ThrottledException;
import com.hello.application.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class PropertyDataAdapter implements IPropertyDataAdapter{

    @Value("${propertydata.demandrate.url}")
    private String propertyDataUrl;

    @Value("${propertydata.growth.url}")
    private String growthUrl;

    @Value("${propertydata.apikey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PropertyDataAdapter.class);

    @Retryable(value = ThrottledException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public DemandRentDto getDemandRentData(String postCode) throws ServiceException {
        try {
            Map<String, String> uriVariables = buildUriVariables(postCode);
            return restTemplate.getForObject(propertyDataUrl, DemandRentDto.class, uriVariables);
        } catch (HttpClientErrorException e) {
            LOGGER.error("getDemandRentData "+e.getMessage());
            throw buildException(e);
        }
    }

    @Retryable(value = ThrottledException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public GrowthDto getGrowthData(String postCode) throws ServiceException {
        try {
            Map<String, String> uriVariables = buildUriVariables(postCode);
            return restTemplate.getForObject(growthUrl, GrowthDto.class, uriVariables);
        } catch (HttpClientErrorException e) {
            LOGGER.error("getGrowthData "+e.getMessage());
            throw buildException(e);
        }
    }

    private Map<String, String> buildUriVariables(String postCode) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("key", apiKey);
        uriVariables.put("postCode", postCode);
        return uriVariables;
    }

    private ServiceException buildException(HttpClientErrorException e) {
        String errorCode = getErrorCode(e);

        switch (errorCode) {
            case "X14":
                return new ThrottledException(e);
            case "X07":
                return new InvalidPostalCodeException(e);
            case "X08":
                return new InsufficientDataException(e);
        }
        return new ServiceException(e);
    }

    private String getErrorCode(HttpClientErrorException exception) {
        try {
            String json = exception.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return (String) map.get("code");

        } catch (JsonProcessingException e) {
            LOGGER.error("getErrorCode "+e.getMessage());
            return ""; // Unable to marshal
        }
    }
}
