package com.frankc137.jinote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories("com.frankc137.jinote.dao.repo")
@SpringBootApplication
@EnableMongoAuditing
public class JinoteServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinoteServerApplication.class, args);
	}

}
