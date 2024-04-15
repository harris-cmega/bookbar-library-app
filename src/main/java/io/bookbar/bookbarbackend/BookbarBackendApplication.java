package io.bookbar.bookbarbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class BookbarBackendApplication {

	public static void main(String[] args) {
			SpringApplication.run(BookbarBackendApplication.class, args);

		System.out.println("We'll start working on the back end soon!");
	}

}
