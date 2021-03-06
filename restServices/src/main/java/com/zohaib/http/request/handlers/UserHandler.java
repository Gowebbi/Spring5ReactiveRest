package com.zohaib.http.request.handlers;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.lang.System.Logger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.zohaib.model.User;
import com.zohaib.service.UserService;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	@Autowired
	private UserService userService;
	 

	private Mono<ServerResponse> buildUserAsServerResponse(final Mono<User> monoUser) {
		return monoUser.flatMap(x -> {
			return ok().body(monoUser, User.class);
		}).switchIfEmpty(ServerResponse.notFound().build());

	}

	public Mono<ServerResponse> getUser(final ServerRequest request) {
		return userService.findByUserId(request.pathVariable("id")).transform(this::buildUserAsServerResponse);
	}

	public Mono<ServerResponse> updateUser(final ServerRequest request)
	{
		return request.bodyToMono(User.class)
				.flatMap( x -> {
				userService.updateUser(Mono.just(x));
				return ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.empty());
		});	
	}

	public Mono<ServerResponse> deleteUser(final ServerRequest request) {
		return Mono.just(request.pathVariable("id")).flatMap(x -> {
			userService.deleteUser(x);
			return ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.empty());
		});
	}

	public Mono<ServerResponse> createUser(final ServerRequest request) {
		return request.bodyToMono(User.class).flatMap( x-> {
			userService.createUser(x.getUserId(), x.getName(), x.getAge());
			return ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.empty());
		});
	}
	
	public Mono<ServerResponse> uploadDocument(final ServerRequest request) {
		System.out.println(request.headers().contentType().orElse(MediaType.TEXT_PLAIN));
       
		request
			.body(BodyExtractors.toMultipartData())
				.flatMap(x -> {
					/*
					Map<String, Part> singleValueMap = x.toSingleValueMap();
					try 
					{
						Part part = singleValueMap.get("documentForUpload");
						
					}
					catch(Exception e)
					{
						return Mono.error(e);
					}
					*/
					return ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.empty()); 
				});
				
		
		
		
		return request.bodyToMono(User.class).flatMap( x-> {
			return ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.empty());
		});
	}
	
}
