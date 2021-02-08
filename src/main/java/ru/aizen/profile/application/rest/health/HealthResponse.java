package ru.aizen.profile.application.rest.health;

import lombok.Getter;

@Getter
public class HealthResponse {

	private final String status;

	public HealthResponse(String status) {
		this.status = status;
	}

}
