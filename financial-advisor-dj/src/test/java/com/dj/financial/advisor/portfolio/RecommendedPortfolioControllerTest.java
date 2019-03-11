package com.dj.financial.advisor.portfolio;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 
 * @author deepikajyotula
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecommendedPortfolioControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * Test Output
     * {"risk":7,"bonds":20,"largeCap":25,"midCap":25,"foreign":25,"smallCap":5}
     * 
     * @throws Exception
     */
    @Test
    public void getRecommendedPortfolio() throws Exception {
    	MvcResult mvcResult = mvc
                .perform(get("/advisorapi/v1.0/recommendedportfolios/7").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();
    	
        mvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.risk", is(7)))
                .andExpect(jsonPath("$.bonds", is(20)))
                .andExpect(jsonPath("$.largeCap", is(25)))
                .andExpect(jsonPath("$.midCap", is(25)))
                .andExpect(jsonPath("$.foreign", is(25)))
                .andExpect(jsonPath("$.smallCap", is(5)));
        
        mvc.perform(MockMvcRequestBuilders.get("/advisorapi/v1.0/recommendedportfolios/20").accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().is4xxClientError());
    }
}