package com.hello.infrastructure;

import com.hello.HelloApplication;
import com.hello.application.service.IRentalDemandAndGrowthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloApplication.class)
@WebAppConfiguration
public class RentalDemandAndGrowthControllerTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private IRentalDemandAndGrowthService rentalDemandAndGrowthService;

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testHappyFlow() throws Exception {

        Mockito.when(rentalDemandAndGrowthService.getCapitalGrowth("W14"))
                .thenReturn("6.6%");

        Mockito.when(rentalDemandAndGrowthService.getRentalDemandRating("W14"))
                .thenReturn("Landlord's market");

        String uri = "/rental-demand-rating-and-capital-growth/W14";
        MvcResult mvcResult = mvc.perform((MockMvcRequestBuilders.get(uri))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(
                "{\"rental_demand_rating\":\"Landlord's market\",\"capital_growth\":\"6.6%\"}",
                response.getContentAsString()
        );
    }
}
