package com.example.service;

import com.example.controller.AddUserReq;
import com.example.repo.UserResourceRelRepo;
import com.example.repo.entity.UserResourceRel;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    private UserResourceRelRepo repo;


    @Override
    public void addUser(AddUserReq req) {
        Long userId = req.getUserId();
        List<String> endPoint = req.getEndPoint();
        if(userId == null || CollectionUtils.isEmpty(endPoint)){
            return;
        }
        List<UserResourceRel> entityList = new ArrayList<>(endPoint.size());
        for(String resource : endPoint){
            UserResourceRel entity = new UserResourceRel();
            entity.setUserId(userId);
            entity.setResource(resource);
            entityList.add(entity);
        }
        repo.saveAllAndFlush(entityList);
    }

    @Override
    public boolean haveAccess(Long userId, String resource) {
        UserResourceRel rel = new UserResourceRel();
        rel.setUserId(userId);
        rel.setResource(resource);
        Example<UserResourceRel> example = Example.of(rel);
        Optional<UserResourceRel> optional = repo.findOne(example);
        return optional.isPresent();
    }


}
