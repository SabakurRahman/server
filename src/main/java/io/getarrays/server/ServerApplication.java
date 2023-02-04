package io.getarrays.server;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){


		return args -> {

			serverRepo.save(new Server(null,"159.168.1.17","Linux6","165 GB",
					"Personal PC8","http:localhost:8090/server/image/server1.png", Status.SERVER_UP));
//			serverRepo.save(new Server(null,"196.167.1.14","Windows","32 GB",
//					"Personal PC2","http:localhost:8090/server/image/server1.png", Status.SERVER_DOWN));
//
//			serverRepo.save(new Server(null,"197.164.1.18","MacOs","128 GB",
//					"Personal PC3","http:localhost:8090/server/image/server1.png", Status.SERVER_UP));
//
//			serverRepo.save(new Server(null,"199.128.1.19","Cloud","512 GB",
//					"Personal PC4","http:localhost:8090/server/image/server1.png", Status.SERVER_DOWN));


		};
	}

}
