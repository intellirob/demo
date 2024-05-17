/*
 * Copyright (c) 2023. lvqing. All rights reserved.
 */
package com.example.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * API全局拦截器
 *
 * @author lvqing
 * @data 2023-7-21
 * @since 1.0.0
 */
@Slf4j
public class GlobalInterceptor implements HandlerInterceptor {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestMethod = "OPTIONS";
        if (StringUtils.equals(requestMethod, request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String mask = request.getHeader("mask");
        UserContext userContext = parseUserContext(mask);
        UserContextHolder.setRequestAttributes(userContext);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.resetUserContext();
    }

    private static UserContext parseUserContext(String mask){
        if(StringUtils.isBlank(mask)){
            return null;
        }
        byte[] bytes = Base64.getDecoder().decode(mask);
        String parseStr = new String(bytes, StandardCharsets.UTF_8);
        if(StringUtils.isBlank(parseStr)){
            return null;
        }
        UserContext userContext = null;
        try {
            userContext = objectMapper.readValue(parseStr, UserContext.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userContext;
    }
}
