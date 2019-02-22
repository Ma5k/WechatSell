package com.mask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.mask.dataobject.mapper")	//使用mybatis时需要配置mapper的包路径
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
