package ru.aizen.profile.domain.user;

import java.util.Collection;

public interface UserRepository {

	long add(User user) throws UserRepositoryException;

	void delete(long studentId) throws UserRepositoryException;

	void update(User user) throws UserRepositoryException;

	Collection<User> findAll() throws UserRepositoryException;

}
