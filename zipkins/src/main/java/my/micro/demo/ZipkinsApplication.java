package my.micro.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinsApplication.class, args);
	}
}
