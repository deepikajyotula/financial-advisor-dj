package com.dj.financial.advisor.personalizedportfolio;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author deepikajyotula
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonalizedPortfolioControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    ObjectMapper objectMapper;

    /**
     * 
     * @throws Exception
     */
    @Test
    public void getRecommendedPortfolio() throws Exception {
    	MvcResult mvcResult = mvc
                .perform(post("/advisorapi/v1.0/recommendedportfolios/7/personalizedportfolio")
                		.accept(MediaType.APPLICATION_JSON)
                		.contentType(MediaType.APPLICATION_JSON)
                		.content(objectMapper.writeValueAsString(new ClientInvestment(300, 20, 400, 0, 40))))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();
    	
        mvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.risk", is(7)))
                .andExpect(jsonPath("$.bonds.difference", is(-148.0)))
                .andExpect(jsonPath("$.bonds.currentAmount", is(300.0)))
                .andExpect(jsonPath("$.bonds.newAmount", is(152.0)))
                .andExpect(jsonPath("$.largeCap.difference", is(170.0)))
                .andExpect(jsonPath("$.largeCap.currentAmount", is(20.0)))
                .andExpect(jsonPath("$.largeCap.newAmount", is(190.0)))
                .andExpect(jsonPath("$.midCap.difference", is(-210.0)))
                .andExpect(jsonPath("$.midCap.currentAmount", is(400.0)))
                .andExpect(jsonPath("$.midCap.newAmount", is(190.0)))
                .andExpect(jsonPath("$.foreign.difference", is(190.0)))
                .andExpect(jsonPath("$.foreign.currentAmount", is(0.0)))
                .andExpect(jsonPath("$.foreign.newAmount", is(190.0)))
                .andExpect(jsonPath("$.smallCap.difference", is(-2.0)))
                .andExpect(jsonPath("$.smallCap.currentAmount", is(40.0)))
                .andExpect(jsonPath("$.smallCap.newAmount", is(38.0)))
                .andExpect(jsonPath("$.description", is("Transfer $190.0 from Mid Cap to Foreign. \nTransfer $148.0 from Bonds to Large Cap. \nTransfer $20.0 from Mid Cap to Large Cap. \nTransfer $2.0 from Small Cap to Large Cap. \n")));
    }
}