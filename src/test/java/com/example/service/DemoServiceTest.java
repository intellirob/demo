package com.example.service;

import com.example.controller.AddUserReq;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class DemoServiceTest {
    @Autowired
    private DemoService demoService;

    @Test
    public void addUser(){
        AddUserReq req = new AddUserReq();
        req.setUserId(1l);
        req.setEndPoint(Arrays.asList("A", "B", "C"));
        demoService.addUser(req);
    }

    @Test
    public void haveAccess(){
        addUser();
        demoService.haveAccess(1l, "A");
    }
}
