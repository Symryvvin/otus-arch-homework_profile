package ru.aizen.profile.domain.user;

import java.util.Collection;

public interface UserRepository {

	long add(User user) throws UserRepositoryException;

	User find(long userId) throws UserRepositoryException;

	void delete(long userId) throws UserRepositoryException;

	void update(User user) throws UserRepositoryException;

	Collection<User> findAll() throws UserRepositoryException;

}
