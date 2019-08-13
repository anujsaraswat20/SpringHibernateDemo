package com.hcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.old","com.hcl"})
public class SpringHibernateTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateTestApplication.class, args);
	}
}
