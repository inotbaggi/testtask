package me.baggi.webrayze.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyExistException extends Exception {
    @Override
    public String getMessage() {
        return "User already exists";
    }
}
