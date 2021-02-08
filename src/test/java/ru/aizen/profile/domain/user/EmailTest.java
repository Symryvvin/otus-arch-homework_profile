package ru.aizen.profile.domain.user;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

	@ParameterizedTest
	@ValueSource(strings = {
			"bestjohn@doe.com",
			"user@domain.com",
			"user@domain.co.in",
			"user.name@domain.com",
			"user_name@domain.com",
			"username@yahoo.corporate.in"})
	public void validEmails(String email) {
		assertDoesNotThrow(() -> Email.from(email));
	}

	@ParameterizedTest
	@ValueSource(strings = {
			".username@yahoo.com",
			"username@yahoo.com.",
			"username@yahoo..com",
			"username@yahoo.c",
			"username@yahoo.corporate"})
	public void invalidEmails(String email) {
		assertThrows(IllegalArgumentException.class, () -> Email.from(email));
	}


}