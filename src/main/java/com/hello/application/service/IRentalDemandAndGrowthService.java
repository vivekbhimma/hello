package com.hello.application.service;

import com.hello.application.service.exception.ServiceException;

public interface IRentalDemandAndGrowthService {
    String getRentalDemandRating(String postCode) throws ServiceException;
    String getCapitalGrowth(String postCode) throws ServiceException;
}
