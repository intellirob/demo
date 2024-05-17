package com.example.controller;

import com.example.config.UserContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addUser() throws Exception {
        UserContext userContext = new UserContext();
        userContext.setUserId(1L);
        userContext.setAccountName("test");
        userContext.setRole("admin");
        AddUserReq req = new AddUserReq();
        req.setUserId(1l);
        req.setEndPoint(Arrays.asList("A", "B", "C"));
        String mask = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(userContext).getBytes(StandardCharsets.UTF_8));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add/addUser");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.header("mask", mask);
        requestBuilder.content(objectMapper.writeValueAsString(req));
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getResource() throws Exception {
        addUser();
        UserContext userContext = new UserContext();
        userContext.setUserId(1L);
        userContext.setAccountName("test");
        userContext.setRole("admin");
        String mask = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(userContext).getBytes(StandardCharsets.UTF_8));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/A");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.header("mask", mask);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
