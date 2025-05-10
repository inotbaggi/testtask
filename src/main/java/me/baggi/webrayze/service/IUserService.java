package me.baggi.webrayze.service;

import me.baggi.webrayze.exception.user.UserAlreadyExistException;
import me.baggi.webrayze.exception.user.UserNotFoundException;
import me.baggi.webrayze.model.User;
import me.baggi.webrayze.request.CreateUserRequest;
import me.baggi.webrayze.request.UpdateUserRequest;

public interface IUserService {
    User createUser(CreateUserRequest request) throws UserAlreadyExistException;

    User getUser(long id);

    User updateUser(long id, UpdateUserRequest request) throws UserNotFoundException;

    void deleteUser(long id) throws UserNotFoundException;
}
