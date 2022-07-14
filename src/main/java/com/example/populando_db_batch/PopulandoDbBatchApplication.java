package com.example.populando_db_batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableBatchProcessing
@SpringBootApplication
public class PopulandoDbBatchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PopulandoDbBatchApplication.class, args);
	}
	
}
