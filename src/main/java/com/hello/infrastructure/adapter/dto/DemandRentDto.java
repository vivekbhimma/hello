package com.hello.infrastructure.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class DemandRentDto {

    private String status;

    @JsonProperty("postcode")
    private String postCode;

    @JsonProperty("postcode_type")
    private String postCodeType;

    @JsonProperty("total_for_rent")
    private BigDecimal totalForRent;

    @JsonProperty("transactions_per_month")
    private Long transactionsPerMonth;

    @JsonProperty("turnover_per_month")
    private String turnoverPerMonth;

    @JsonProperty("months_of_inventory")
    private String monthsOfInventory;

    @JsonProperty("days_on_market")
    private Long daysOnMarket;

    @JsonProperty("rental_demand_rating")
    private String rentalDemandRating;

    @JsonProperty("process_time")
    private String processTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCodeType() {
        return postCodeType;
    }

    public void setPostCodeType(String postCodeType) {
        this.postCodeType = postCodeType;
    }

    public BigDecimal getTotalForRent() {
        return totalForRent;
    }

    public void setTotalForRent(BigDecimal totalForRent) {
        this.totalForRent = totalForRent;
    }

    public Long getTransactionsPerMonth() {
        return transactionsPerMonth;
    }

    public void setTransactionsPerMonth(Long transactionsPerMonth) {
        this.transactionsPerMonth = transactionsPerMonth;
    }

    public String getTurnoverPerMonth() {
        return turnoverPerMonth;
    }

    public void setTurnoverPerMonth(String turnoverPerMonth) {
        this.turnoverPerMonth = turnoverPerMonth;
    }

    public String getMonthsOfInventory() {
        return monthsOfInventory;
    }

    public void setMonthsOfInventory(String monthsOfInventory) {
        this.monthsOfInventory = monthsOfInventory;
    }

    public Long getDaysOnMarket() {
        return daysOnMarket;
    }

    public void setDaysOnMarket(Long daysOnMarket) {
        this.daysOnMarket = daysOnMarket;
    }

    public String getRentalDemandRating() {
        return rentalDemandRating;
    }

    public void setRentalDemandRating(String rentalDemandRating) {
        this.rentalDemandRating = rentalDemandRating;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }
}
