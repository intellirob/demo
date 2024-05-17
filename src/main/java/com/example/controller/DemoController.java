package com.example.controller;

import com.example.config.UserContext;
import com.example.config.UserContextHolder;
import com.example.service.DemoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping("/add/addUser")
    public ApiResponse addUser(@RequestBody AddUserReq req){
        UserContext userContext = UserContextHolder.getUserContext();
        if(userContext == null || !"admin".equals(userContext.getRole())){
            return new ApiResponse(1, "you have no access");
        }
        if(req.getUserId() == null){
            return new ApiResponse(1, "parameter:userId can not bu null");
        }
        if(CollectionUtils.isEmpty(req.getEndPoint())){
            return new ApiResponse(1, "parameter:endpoint can not bu empty");
        }
        demoService.addUser(req);
        return ApiResponse.success();
    }

    @GetMapping("/user/{resource}")
    public ApiResponse getResource(@PathVariable("resource") String resource){
        UserContext userContext = UserContextHolder.getUserContext();
        if(userContext == null || userContext.getUserId() == null){
            return new ApiResponse(1, "failure，user info lost");
        }
        boolean haveAccess = demoService.haveAccess(userContext.getUserId(), resource);
        if(haveAccess){
            return ApiResponse.success();
        }else{
            return new ApiResponse(1, "failure，you have no access to the resource");
        }
    }




}
