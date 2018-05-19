package com.zohaib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.zohaib.routers.ApiRouter;

@Configuration
@EnableWebFlux
public class WebConifg {

	@Bean 
	public ApiRouter apiRouter()
	{
		return new ApiRouter();
	}
	
	@Bean
	public RouterFunction<?> mainRouterFunction(final ApiRouter apiRouter)
	{
		return apiRouter.buildAllRoutes();
	}
}
