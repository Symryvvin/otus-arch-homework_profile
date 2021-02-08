package ru.aizen.profile.infrastructure.repsistence.user;

import org.springframework.stereotype.Repository;
import ru.aizen.profile.domain.user.User;
import ru.aizen.profile.domain.user.UserRepository;
import ru.aizen.profile.domain.user.UserRepositoryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
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
	public User find(long userId) {
		return storage.get(userId);
	}

	@Override
	public void delete(long userId) throws UserRepositoryException {
		if (storage.get(userId) != null) {
			storage.remove(userId);
		} else {
			throw new UserRepositoryException("User with id " + userId + " not found");
		}
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
