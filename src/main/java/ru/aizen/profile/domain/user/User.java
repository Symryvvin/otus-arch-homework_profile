package ru.aizen.profile.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

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
		Assert.hasLength(username, "Username is required");
		if (username.length() > 256) {
			throw new IllegalArgumentException("The username must be a string with a maximum length of " + MAX_USERNAME_LENGTH);
		}
		return new User(username, firstName, lastName, Email.from(email), Phone.from(phone));
	}

}
