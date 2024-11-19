package com.af.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class TransactionalService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class,timeout = 10)
    public void add(){
        String sql = "insert into user(name, age) values (?, ?)";
        Object[] args = new Object[]{"小明",System.currentTimeMillis()};
        jdbcTemplate.update(sql, args);
        System.out.println("事务测试-001-新增数据");
//        try{
//            Thread.sleep(12000);
//        }catch (Exception e){
//            System.out.println("sleep报错了");
//        }
        String sql2 = "update user set age = 2 where id in (1,2)";
        jdbcTemplate.update(sql2);

        System.out.println("事务测试-001-更新数据");
//        throw new RuntimeException("保存出现异常...");
    }
}
