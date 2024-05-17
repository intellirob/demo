package com.example.repo;


import com.example.repo.entity.UserResourceRel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserResourceRelRepoTest {
    @Autowired
    private UserResourceRelRepo repo;

    @Test
    void add(){
        UserResourceRel entity = new UserResourceRel();
        entity.setUserId(1L);
        entity.setResource("A");
        repo.saveAndFlush(entity);
    }
}
