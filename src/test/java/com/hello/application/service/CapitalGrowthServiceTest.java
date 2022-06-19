package com.hello.application.service;

import com.hello.infrastructure.adapter.IPropertyDataAdapter;
import com.hello.infrastructure.adapter.dto.GrowthDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class CapitalGrowthServiceTest {

    @Mock
    protected IPropertyDataAdapter propertyDataAdapter;

    @InjectMocks
    protected RentalDemandAndGrowthService rentalDemandAndGrowthService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCapitalGrowth_AllTheData() {
        try {
            GrowthDto growthDto = new GrowthDto();
            List dataList = new ArrayList<List<Object>>();
            growthDto.setData(dataList);

            addToList(dataList, "Aug 2013",862199,null);
            addToList(dataList, "Aug 2014",1039052,"20.5%");
            addToList(dataList, "Aug 2015",1069005,"2.9%");
            addToList(dataList, "Aug 2016",1077210,"0.8%");
            addToList(dataList, "Aug 2017",1123564,"4.3%");
            addToList(dataList, "Aug 2018",1109593,"-1.2%");
            addToList(dataList, "Aug 2011",1039052,"22.5%");
            addToList(dataList, "Aug 2010",1039052,"23.5%");
            addToList(dataList, null,1109593,"-1.2%");
            addToList(dataList, null,null,null);

            Mockito.when(propertyDataAdapter.getGrowthData("123")).thenReturn(growthDto);
            String capitalGrowth = rentalDemandAndGrowthService.getCapitalGrowth("123");

            Assertions.assertEquals(capitalGrowth, "-1.2%");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testGetCapitalGrowth_NoData() {
        try {
            GrowthDto growthDto = new GrowthDto();
            List dataList = new ArrayList<List<Object>>();
            growthDto.setData(dataList);

            Mockito.when(propertyDataAdapter.getGrowthData("123")).thenReturn(growthDto);
            String capitalGrowth = rentalDemandAndGrowthService.getCapitalGrowth("123");

            Assertions.assertEquals(capitalGrowth, null);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    public void addToList(List<List<Object>> dataList,
                          String date,
                          Integer growthFigure,
                          String growthPercentage) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(date);
        list.add(growthFigure);
        list.add(growthPercentage);
        dataList.add(list);
    }
}
