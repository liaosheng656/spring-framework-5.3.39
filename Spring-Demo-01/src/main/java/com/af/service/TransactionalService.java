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
        //如果后续不需要执行SQL了，并且后续的程序没有报错/发生异常，那么就是存在卡顿或长时间处理不完
		//那么也是一直在事务中的，设置了超期时间，也没有用的，等后续程序执行完也一样会提交事务，而其他事务
//        try{
//            Thread.sleep(12000);
//        }catch (Exception e){
//            System.out.println("sleep报错了");
//        }
		String sql3 = "delete from  user where id = 1";
		jdbcTemplate.update(sql3);
		System.out.println("事务测试-001-删除数据");

		try{
			Thread.sleep(12000);
		}catch (Exception e){
			System.out.println("sleep报错了");
		}

		String sql4 = "select age from user where id = 2";
		Long age = jdbcTemplate.queryForObject(sql4, Long.class);
		System.out.println("事务测试-001-查询数据age="+age);

//        throw new RuntimeException("保存出现异常...");
    }
}
