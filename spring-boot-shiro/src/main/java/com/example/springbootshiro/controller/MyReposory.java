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

    public List<Map<String, Object>> list() {
        String sql = "select * from kf_cart";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> login(String username, String password) {
        String sql = "select * from kf_admin where username = '" + username + "' and password = '" + password + "'";
        return jdbcTemplate.queryForList(sql);
    }

    public void saveUserToken(String id, int userId, String token) {
        String sql = "insert into kf_token ( id, userId, token) values (?,?,?)";
        jdbcTemplate.update(sql, id, userId, token);
    }

    public List<Map<String,Object>> getToken(String token){
        String sql = "select * from kf_token where token = ?";
        return jdbcTemplate.queryForList(sql,token);
    }
}
