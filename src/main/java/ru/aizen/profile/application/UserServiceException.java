package ru.aizen.profile.application;

public class UserServiceException extends Exception {

	public UserServiceException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

}
