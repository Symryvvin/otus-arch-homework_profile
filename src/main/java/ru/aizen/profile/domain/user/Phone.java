package ru.aizen.profile.domain.user;

import java.util.regex.Pattern;

public class Phone {

	private static final String PHONE_FORMAT_REGEXP = "(\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})";
	private static final String PHONE_REPLACEMENT = "+$1($2)$3-$4-$5";
	private static final Pattern phoneNumberPattern = Pattern.compile("\\d{11}");
	private static final String SYMBOLS = "[ +-/(/)]*";

	private final long phoneNumber;

	private Phone(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static Phone from(String phoneNumber) {
		String trimmed = phoneNumber.replaceAll(SYMBOLS, "");
		if (phoneNumberPattern.matcher(trimmed).matches()) {
			return new Phone(Long.parseLong(trimmed));
		} else {
			throw new IllegalArgumentException("Wrong phone number. Must contain 11 digits and may contains + ( ) - symbols");
		}
	}

	public static Phone from(long phoneNumber) {
		return new Phone(phoneNumber);
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public String getFormattedPhone() {
		return Long.toString(phoneNumber).replaceFirst(PHONE_FORMAT_REGEXP, PHONE_REPLACEMENT);
	}

}
