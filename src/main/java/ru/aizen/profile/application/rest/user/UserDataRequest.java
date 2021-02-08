package ru.aizen.profile.application.rest.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDataRequest {

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;

}
