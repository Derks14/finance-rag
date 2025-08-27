package sika;

import org.springframework.boot.SpringApplication;

public class TestSikaApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
