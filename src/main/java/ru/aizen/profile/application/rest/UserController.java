package ru.aizen.profile.application.rest;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aizen.profile.application.UserService;
import ru.aizen.profile.application.UserServiceException;
import ru.aizen.profile.domain.user.User;

import java.util.Collection;

@RestController
@RequestMapping("api/v1")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(path = "/user",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody UserDataRequest request) throws UserServiceException {
		userService.create(
				request.getUsername(),
				request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				request.getPhone());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/user/{userId}")
	public ResponseEntity<Void> delete(@PathVariable("userId") long userId) throws UserServiceException {
		userService.delete(userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping(path = "/user/{userId}")
	public ResponseEntity<Void> update(@PathVariable("userId") long userId, @RequestBody UserDataRequest request) throws UserServiceException {
		userService.update(userId,
				request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				request.getPhone());
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/user/{userId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User findUser(@PathVariable("userId") long userId) throws UserServiceException {
		return userService.findUser(userId);
	}

	@GetMapping(path = "/users",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<User> users() throws UserServiceException {
		return userService.getAllUsers();
	}


}
