package com.zohaib.routers;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.zohaib.http.request.handlers.UserHandler;

public class ApiRouter {

	@Autowired
	private UserHandler userHandler;

	public RouterFunction<?> buildAllRoutes() {
		return nest(RequestPredicates.path("/api"), 
				buildGetRoutes()
					.andOther(buildPostRoutes())
					.andOther(buildDeleteRoutes())
					.andOther(buildPutRoutes())
				);
				
	}

	private RouterFunction<?> buildGetRoutes() {
		return route(
					RequestPredicates.GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON))
					, userHandler::getUser);
	}

	private RouterFunction<?> buildPostRoutes() {
		return nest(accept(MediaType.APPLICATION_JSON),
				route(RequestPredicates.POST("/users/{id}"), userHandler::updateUser));
	}

	private RouterFunction<?> buildDeleteRoutes() {
		return nest(accept(MediaType.APPLICATION_JSON),
				route(RequestPredicates.DELETE("/users/{id}"), userHandler::deleteUser));

	}

	private RouterFunction<?> buildPutRoutes() {
		return nest(accept(MediaType.APPLICATION_JSON),
				route(RequestPredicates.PUT("/users"), userHandler::createUser));

	}
}
