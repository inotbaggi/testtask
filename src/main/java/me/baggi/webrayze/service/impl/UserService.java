package me.baggi.webrayze.service.impl;

import lombok.SneakyThrows;
import me.baggi.webrayze.exception.user.UserAlreadyExistException;
import me.baggi.webrayze.exception.user.UserNotFoundException;
import me.baggi.webrayze.model.User;
import me.baggi.webrayze.repository.UserRepository;
import me.baggi.webrayze.request.CreateUserRequest;
import me.baggi.webrayze.request.UpdateUserRequest;
import me.baggi.webrayze.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @SneakyThrows
    public User createUser(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            logger.warn("Attempt to create user! Username already exists: {}", request.getUsername());
            throw new UserAlreadyExistException();
        }

        User user = new User();
        user.setUsername(request.getUsername());

        userRepository.save(user);
        logger.info("User created! ({})", user.getId());
        return user;
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(long id, UpdateUserRequest request) throws UserNotFoundException {
        User user = getUser(id);
        if (user == null) {
            logger.warn("Attempt to update user! User not found by id: {}", id);
            throw new UserNotFoundException();
        }

        user.setUsername(request.getUsername());

        userRepository.save(user);
        logger.info("User({}) successfully updated!", id);
        return user;
    }

    @Override
    public void deleteUser(long id) throws UserNotFoundException {
        User user = getUser(id);
        if (user == null) {
            logger.warn("Attempt to delete user! User not found by id: {}", id);
            throw new UserNotFoundException();
        }
        userRepository.delete(user);
        logger.info("User({}) successfully deleted!", id);
    }
}
