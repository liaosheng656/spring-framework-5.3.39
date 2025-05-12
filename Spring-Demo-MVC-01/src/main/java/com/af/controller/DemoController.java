package com.af.controller;


import com.af.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/test")
	@ResponseBody
	public User test() {
		User user = new User();
		user.setName("小明");
		user.setAge(18);
		return user;
	}


	@GetMapping("/test02")
	@ResponseBody
	public String test02() {
		return "执行test02方法";
	}
}
