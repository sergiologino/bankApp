package ru.savkins.bankApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankAppApplication.class, args);
	}

}
