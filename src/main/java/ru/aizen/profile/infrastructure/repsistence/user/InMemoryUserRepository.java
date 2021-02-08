package ru.aizen.profile.infrastructure.repsistence.user;

import ru.aizen.profile.domain.user.User;
import ru.aizen.profile.domain.user.UserRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

	private final Map<Long, User> storage;
	private long sequence;

	public InMemoryUserRepository() {
		this.storage = new HashMap<>();
		this.sequence = 0L;
	}

	@Override
	public long add(User user) {
		long key = ++sequence;
		user.setId(key);
		storage.put(key, user);
		return key;
	}

	@Override
	public void delete(long userId) {
		storage.remove(userId);
	}

	@Override
	public void update(User user) {
		storage.put(user.getId(), user);
	}

	@Override
	public Collection<User> findAll() {
		return storage.values();
	}

}
