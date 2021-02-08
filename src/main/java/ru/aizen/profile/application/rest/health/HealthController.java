package ru.aizen.profile.application.rest.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

	@Value("${app.version}")
	private String version;

	private final Environment env;

	@Autowired
	public HealthController(Environment env) {
		this.env = env;
	}

	@GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HealthResponse> health() {
		return ResponseEntity.ok(new HealthResponse("OK"));
	}

	@GetMapping(value = "/version", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> version() {
		return ResponseEntity.ok(version);
	}

	@GetMapping("/ready")
	public ResponseEntity<Void> ready() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/datasource")
	public ResponseEntity<Map<String, String>> datasource() {
		Map<String, String> dataSourceEnv = new HashMap<>();
		dataSourceEnv.put("HOST", env.getProperty("spring.datasource.url"));
		dataSourceEnv.put("DRIVER", env.getProperty("spring.datasource.driverClassName"));
		dataSourceEnv.put("USERNAME", env.getProperty("spring.datasource.username"));
		dataSourceEnv.put("PASSWORD", env.getProperty("spring.datasource.password"));
		return ResponseEntity.ok(dataSourceEnv);
	}

}
