package ru.aizen.profile.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "USER")
@Table(name = "USER")
public class User {

	private static final int MAX_USERNAME_LENGTH = 256;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "USERNAME", nullable = false)
	private String username;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	@Convert(converter = EmailConverter.class)
	private Email email;
	@Column(name = "PHONE")
	@Convert(converter = PhoneConverter.class)
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
