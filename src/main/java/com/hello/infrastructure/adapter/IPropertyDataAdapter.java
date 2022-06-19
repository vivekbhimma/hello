package com.hello.infrastructure.adapter;

import com.hello.infrastructure.adapter.dto.DemandRentDto;
import com.hello.infrastructure.adapter.dto.GrowthDto;
import com.hello.application.service.exception.ServiceException;

public interface IPropertyDataAdapter {
    DemandRentDto getDemandRentData(String postCode) throws ServiceException;
    GrowthDto getGrowthData(String postCode) throws ServiceException;
}
