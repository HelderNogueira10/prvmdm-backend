package com.privguard.mdm.server;

import com.privguard.mdm.server.global.RepositoriesHub;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static final String API_VERSION = "/api/v1";
	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);

	}

}
