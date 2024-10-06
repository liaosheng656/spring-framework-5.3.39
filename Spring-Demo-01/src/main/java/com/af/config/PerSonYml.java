package com.af.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(name = "PerSonYml", value = "classpath:MyPropertyYml.yml",
		encoding = "UTF-8",factory = YamlPropertySourceFactory.class)
public class PerSonYml {

	@Value("${person.id}")
	private String id;

	@Value("${person.name}")
	private String name;

	@Value("${person.age}")
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
		return "PerSonYml{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
