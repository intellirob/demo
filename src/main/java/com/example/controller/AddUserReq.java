package com.example.controller;

import lombok.Data;

import java.util.List;

@Data
public class AddUserReq {
   private Long userId;
   private List<String> endPoint;
}
