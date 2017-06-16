
package com.containerstore.whereis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WhereIsControllerTest {
    @Autowired private MockMvc mvc;

    @Test
    public void getInitialForm() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name(equalTo("whereis")));
    }
    
    @Test
    public void getRoomLocationList() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/conference_rooms"))
            .andExpect(status().isOk())
            .andExpect(view().name(equalTo("WhereisList")));
    }
    
    @Test
    public void testProcessRequest() throws Exception {
    	WhereIsController ctr = new WhereIsController();
    	String[] result = ctr.processRequest("test");
    	String[] expected = {"somewhere, but I don't know where"};
    	assertEquals(expected[0], result[0]);
    	//assertArrayEquals(expected, result);
    	
    }
}
