package com.jachs.jbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class JBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(JBatchApplication.class, args);
	}

}
