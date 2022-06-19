package com.hello.application.service;

import com.hello.application.service.exception.ServiceException;
import com.hello.infrastructure.adapter.IPropertyDataAdapter;
import com.hello.infrastructure.adapter.dto.DemandRentDto;
import com.hello.infrastructure.adapter.dto.GrowthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RentalDemandAndGrowthService implements IRentalDemandAndGrowthService {

    @Autowired
    private IPropertyDataAdapter propertyDataAdapter;

    @Cacheable(value = "demand_cache", key = "#postCode")
    public String getRentalDemandRating(String postCode) throws ServiceException {
        DemandRentDto demandRentData = propertyDataAdapter.getDemandRentData(postCode);
        return demandRentData.getRentalDemandRating();
    }

    @Cacheable(value = "growth_cache", key = "#postCode")
    public String getCapitalGrowth(String postCode) throws ServiceException {
        GrowthDto growthData = propertyDataAdapter.getGrowthData(postCode);
        Optional<List<Object>> recentCapitalGrowth = growthData
                .getData()
                .stream()
                .filter(data -> data.get(1) != null && data.get(0) != null)
                .max(Comparator.comparing(
                        data -> {
                            String dateString = (String) data.get(0);
                            return Integer.parseInt((dateString.split(" "))[1]);
                        }
                ));

        if (recentCapitalGrowth.isPresent()) {
            return (String) recentCapitalGrowth.get().get(2);
        } else {
            return null;
        }
    }
}
