package ru.aizen.profile.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneTest {

	@ParameterizedTest
	@ValueSource(strings = {
			"+71234567890",
			"71234567890",
			"7 123 456-78-90",
			"7(123)4567890"})
	public void createPhone(String number) {
		Phone phone = Phone.from(number);

		assertEquals("+7(123)456-78-90", phone.getFormattedPhone());
		assertEquals(71234567890L, phone.getPhoneNumber());
	}

	@Test
	public void createInvalidPhone() {
		assertThrows(IllegalArgumentException.class, () -> Phone.from("number11111111"));
	}

}