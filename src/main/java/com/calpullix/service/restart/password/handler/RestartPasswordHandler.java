package com.calpullix.service.restart.password.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.calpullix.service.restart.password.model.RestartPasswordRequest;
import com.calpullix.service.restart.password.service.RestartPasswordService;
import com.calpullix.service.restart.password.util.AbstractWrapper;
import com.calpullix.service.restart.password.util.ValidationHandler;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RestartPasswordHandler {

	@Value("${app.message-error-location-body}")
	private String messageErrorLocationBody;

	@Autowired
	private RestartPasswordService restartPasswordService;
	
	@Autowired
	private ValidationHandler validationHandler;

	@Timed(value = "calpullix.service.restart.password.metrics", description = "Restart password operation")
	public Mono<ServerResponse> restartPassword(ServerRequest serverRequest) {
		log.info(":: Restart Password Handler {} ", serverRequest);
		return validationHandler.handle(input -> input.flatMap(request -> AbstractWrapper.async(() -> {
			return restartPasswordService.restartPassword(request);
		})).flatMap(response -> ServerResponse.ok().body(BodyInserters.fromObject(response))), serverRequest,
				RestartPasswordRequest.class).switchIfEmpty(Mono.error(new Exception(messageErrorLocationBody)));
	}
	
	@Timed(value = "calpullix.service.change.password.metrics", description = "Change password operation")
	public Mono<ServerResponse> changePassword(ServerRequest serverRequest) {
		log.info(":: Change Password Handler {} ", serverRequest);
		return validationHandler.handle(input -> input.flatMap(request -> AbstractWrapper.async(() -> {
			return restartPasswordService.changePassword(request);
		})).flatMap(response -> ServerResponse.ok().body(BodyInserters.fromObject(response))), serverRequest,
				RestartPasswordRequest.class).switchIfEmpty(Mono.error(new Exception(messageErrorLocationBody)));
	}
	

}
