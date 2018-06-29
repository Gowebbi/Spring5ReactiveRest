package com.zohaib.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zohaib.model.User;

import reactor.core.publisher.Mono;

@Service
public class UserService {

	private static Map<String, User> users = new LinkedHashMap<String, User>();

	static {
		users.put("anna", User.newUser("anna", "Anna Poli", 23));
		users.put("lucy", User.newUser("lucy", "Lucy Immortal", 34));
	}

	public Mono<User> findByUserId(final String userId) {
		return Mono.justOrEmpty(users.values().stream()
				.filter(x -> (x.getUserId() != null) && x.getUserId().equalsIgnoreCase(userId)).findFirst());
	}

	public Mono<User> createUser(final String userId, final String name, final int age) {
		return findByUserId(userId).flatMap(x -> {

			if (x == null) 
			{
				throw new IllegalArgumentException("User id '" 
						+ userId 
						+ "' already has taken. Try some other user id!");
			}
			users.put(userId, User.newUser(userId, name, age));
			return findByUserId(userId);
		});

	}

	public void deleteUser(final String userId) {
		users.remove(userId);
	}

	public void updateUser(final Mono<User> givenUserToUpdate) {
		/*
		givenUserToUpdate.map(x -> {
			
			if(x == null)
			{
				throw new IllegalArgumentException("Given user object is null!");
			}
			updateUser(x.getUserId(), x.getName(), x.getAge());
			
		});
		*/
	}

	public void updateUser(final String userId, final String name, final int age) {
		
		findByUserId(userId).flatMap( x -> {
			if (x != null) {
				throw new IllegalArgumentException("Cannot find user with user id: " + userId);
			}
			users.remove(userId);
			return createUser(userId, name, age);
			
		});

	}
	
	public void uploadFile()
	{
		
	}
}
