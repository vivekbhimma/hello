package com.hello.infrastructure.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentalDemandAndGrowthResponse {

    @JsonProperty("rental_demand_rating")
    private String rentalDemandRating;

    @JsonProperty("capital_growth")
    private String capitalGrowth;

    @JsonProperty("error_message")
    private String errorMessage;

    public String getRentalDemandRating() {
        return rentalDemandRating;
    }

    public void setRentalDemandRating(String rentalDemandRating) {
        this.rentalDemandRating = rentalDemandRating;
    }

    public String getCapitalGrowth() {
        return capitalGrowth;
    }

    public void setCapitalGrowth(String capitalGrowth) {
        this.capitalGrowth = capitalGrowth;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
