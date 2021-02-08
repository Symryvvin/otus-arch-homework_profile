package ru.aizen.profile.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aizen.profile.domain.user.Email;
import ru.aizen.profile.domain.user.Phone;
import ru.aizen.profile.domain.user.User;
import ru.aizen.profile.domain.user.UserRepository;

import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void create(String username, String firstName, String lastName, String email, String phone) {
		User user = User.from(username, firstName, lastName, email, phone);
		userRepository.save(user);
	}

	public void delete(long userId) {
		userRepository.deleteById(userId);
	}

	public void update(long userId, String firstName, String lastName, String email, String phone)
			throws UserServiceException {
		Optional<User> userOptional = userRepository.findById(userId);

		if (userOptional.isPresent()) {
			User user = userOptional.get();

			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(Phone.from(phone));
			user.setEmail(Email.from(email));

			userRepository.save(user);
		} else {
			throw new UserServiceException("User with id " + userId + " not found");
		}
	}

	public User findUser(long userId) throws UserServiceException {
		Optional<User> userOptional = userRepository.findById(userId);

		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new UserServiceException("User with id " + userId + " not found");
		}
	}

}
