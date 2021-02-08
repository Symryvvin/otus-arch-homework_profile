package ru.aizen.profile.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aizen.profile.domain.user.*;

import java.util.Collection;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void create(String username, String firstName, String lastName, String email, String phone) throws UserServiceException {
		try {
			User user = User.from(username, firstName, lastName, email, phone);
			userRepository.add(user);
		} catch (UserRepositoryException e) {
			throw new UserServiceException(e);
		}
	}

	public void delete(long userId) throws UserServiceException {
		try {
			userRepository.delete(userId);
		} catch (UserRepositoryException e) {
			throw new UserServiceException(e);
		}
	}

	public void update(long userId, String firstName, String lastName, String email, String phone) throws UserServiceException {
		try {
			User user = userRepository.find(userId);

			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(Phone.from(phone));
			user.setEmail(Email.from(email));

			userRepository.update(user);
		} catch (UserRepositoryException e) {
			throw new UserServiceException(e);
		}
	}

	public Collection<User> getAllUsers() throws UserServiceException {
		try {
			return userRepository.findAll();
		} catch (UserRepositoryException e) {
			throw new UserServiceException(e);
		}
	}

	public User findUser(long userId) throws UserServiceException {
		try {
			return userRepository.find(userId);
		} catch (UserRepositoryException e) {
			throw new UserServiceException(e);
		}
	}

}
