package com.example.repo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="user_resource_rel")
@Data
public class UserResourceRel {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private Long userId;

    private String resource;
}
