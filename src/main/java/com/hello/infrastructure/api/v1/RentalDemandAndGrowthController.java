package com.hello.infrastructure.api.v1;

import com.hello.application.service.IRentalDemandAndGrowthService;
import com.hello.application.service.exception.InsufficientDataException;
import com.hello.application.service.exception.InvalidPostalCodeException;
import com.hello.application.service.exception.ServiceException;
import com.hello.infrastructure.api.v1.dto.RentalDemandAndGrowthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
public class RentalDemandAndGrowthController {

    @Autowired
    private IRentalDemandAndGrowthService rentalDemandAndGrowthService;

    @RequestMapping("/rental-demand-rating-and-capital-growth/{postCode}")
    public @ResponseBody
    ResponseEntity<RentalDemandAndGrowthResponse> getRentalDemandRatingAndCapitalGrowth(
            @NotNull @PathVariable("postCode") final String postCode,
            final HttpServletRequest servletRequest
    ) {

        RentalDemandAndGrowthResponse rentalDemandAndGrowthResponse =
                new RentalDemandAndGrowthResponse();

        try {
            String rentalDemandRating = rentalDemandAndGrowthService.getRentalDemandRating(postCode.toUpperCase());
            String capitalGrowth = rentalDemandAndGrowthService.getCapitalGrowth(postCode.toUpperCase());

            rentalDemandAndGrowthResponse.setRentalDemandRating(rentalDemandRating);
            rentalDemandAndGrowthResponse.setCapitalGrowth(capitalGrowth);
            return new ResponseEntity<RentalDemandAndGrowthResponse>(rentalDemandAndGrowthResponse, HttpStatus.OK);

        } catch (InvalidPostalCodeException| InsufficientDataException exception) {
            rentalDemandAndGrowthResponse.setErrorMessage(exception.getErrorMessage());
            return new ResponseEntity<RentalDemandAndGrowthResponse>(
                    rentalDemandAndGrowthResponse,
                    HttpStatus.BAD_REQUEST);
        } catch (ServiceException exception) {
            rentalDemandAndGrowthResponse.setErrorMessage(exception.getErrorMessage());
            return new ResponseEntity<RentalDemandAndGrowthResponse>(
                    rentalDemandAndGrowthResponse,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
