package com.example.springbootshiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MyReposory {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> list(){
        String sql = "select * from kf_cart";
        return jdbcTemplate.queryForList(sql);
    }
}
