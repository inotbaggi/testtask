package me.baggi.webrayze.controller.user;

import me.baggi.webrayze.dto.UserDTO;
import me.baggi.webrayze.exception.user.UserAlreadyExistException;
import me.baggi.webrayze.exception.user.UserNotFoundException;
import me.baggi.webrayze.mapper.UserMapper;
import me.baggi.webrayze.model.User;
import me.baggi.webrayze.request.CreateUserRequest;
import me.baggi.webrayze.request.UpdateUserRequest;
import me.baggi.webrayze.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id) {
        User user = userService.getUser(id);
        return user != null ? ResponseEntity.ok(userMapper.toDTO(user)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody() CreateUserRequest body) throws UserAlreadyExistException {
        User user = userService.createUser(body);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable long id,
            @RequestBody UpdateUserRequest body
    ) throws UserNotFoundException {
        User user = userService.updateUser(id, body);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(
            @PathVariable long id
    ) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok(true);
    }
}
