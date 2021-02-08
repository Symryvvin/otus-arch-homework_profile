package ru.aizen.profile.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

	private static final int MAX_USERNAME_LENGTH = 256;

	private long id;
	private String username;
	private String firstName;
	private String lastName;
	private Email email;
	private Phone phone;

	private User(String username, String firstName, String lastName, Email email, Phone phone) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	public static User from(String username, String firstName, String lastName, String email, String phone) {
		return new User(username, firstName, lastName, Email.from(email), Phone.from(phone));
	}

}
