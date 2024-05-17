package com.example.service;

import com.example.controller.AddUserReq;

public interface DemoService {

    void addUser(AddUserReq req);


    boolean haveAccess(Long userId, String resource);
}
