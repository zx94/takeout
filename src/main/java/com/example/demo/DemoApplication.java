package com.example.demo;

import com.example.demo.helper.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@MapperScan("com.example.demo.mapper")
@SpringBootApplication
public class DemoApplication {

	@Bean
	public SnowflakeIdWorker snowflakeIdWorker() {
		return new SnowflakeIdWorker(1, 1);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
