package com.hello.infrastructure.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GrowthDto {

    private String status;

    @JsonProperty("postcode")
    private String postCode;

    @JsonProperty("postcode_type")
    private String postCodeType;

    private String url;

    private List<List<Object>> data;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }
}
