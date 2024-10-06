package com.af.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(name = "PerSonProperties", value = "classpath:MyProperty.properties",encoding = "UTF-8")
public class PerSonProperties {

	@Value("${person.id1}")
	private String id;

	@Value("${person.name1}")
	private String name;

	@Value("${person.age1}")
	private Integer age;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "PerSonProperties{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
