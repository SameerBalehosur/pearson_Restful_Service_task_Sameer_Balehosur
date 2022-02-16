package com.ty.storecsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class StoreCsvRestfulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreCsvRestfulServiceApplication.class, args);
	}

}
