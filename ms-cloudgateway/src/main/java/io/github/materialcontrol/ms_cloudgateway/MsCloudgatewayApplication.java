package io.github.materialcontrol.ms_cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCloudgatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
					.route(r -> r.path("/api/v1/users/**").uri("lb://ms-users"))
					.route(r -> r.path("/api/v1/materials/**", "/api/v1/items/**").uri("lb://ms-materials"))
					.route(r -> r.path("/api/v1/loan/**").uri("lb://ms-loan"))
				.build();
	}
}
