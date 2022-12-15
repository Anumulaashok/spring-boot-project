package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AshuApplication {

	public static void main(String[] args) {
		SpringApplication.run(AshuApplication.class, args);
//		final NgrokClient ngrokClient = new NgrokClient.Builder().build();
//        final CreateTunnel createTunnel = new CreateTunnel.Builder()
//                        .withAddr(8999)
//                        .build();
//        final Tunnel tunnel = ngrokClient.connect(createTunnel);
	}

}
