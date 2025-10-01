package com.loaraid.raidscheduler;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoaraidApplication {

	public static void main(String[] args) {
		//env파일 로드
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});

		SpringApplication.run(LoaraidApplication.class, args);
	}

}
