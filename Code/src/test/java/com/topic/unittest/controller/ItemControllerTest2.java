package com.topic.unittest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.topic.unittest.service.ItemBusinessService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemBusinessService businessService;

    @Test
    public void itemFromBusinessService_basic() throws Exception {
        // when(businessService.retreiveHardcodedItem()).thenReturn(new Item(2, "Item2", 10, 10));

        RequestBuilder request =
            MockMvcRequestBuilders.get("/item-from-service-service").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
            .andExpect(content().json(" {\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100,\"value\":0}"))
            .andReturn();
        // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

}
