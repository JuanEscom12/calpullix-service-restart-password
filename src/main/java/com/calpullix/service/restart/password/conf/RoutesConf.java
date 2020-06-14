package com.calpullix.service.restart.password.conf;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.calpullix.service.restart.password.handler.RestartPasswordHandler;

@Configuration
public class RoutesConf {

	@Value("${app.path-restart-login}")
	private String pathRestartPassword;
	
	@Value("${app.path-change-pasword}")
	private String pathChangePassword;
	
	@Bean
	public RouterFunction<ServerResponse> routesLogin(RestartPasswordHandler restartPasswordHandler) {
		return route(POST(pathRestartPassword), restartPasswordHandler::restartPassword)
				.and(route(POST(pathChangePassword), restartPasswordHandler::changePassword));
	}
	
}
