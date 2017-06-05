package com.liendeedusa.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiendeedSuportApplication {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/home/ubuntu/chromedriver");

		SpringApplication.run(LiendeedSuportApplication.class, args);
	}
}
