package vn.tdat.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.tdat.jobhunter.domain.User;
import vn.tdat.jobhunter.service.UserService;
import vn.tdat.jobhunter.util.error.IdInvalidException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<User> getUser(@RequestBody User postmanUser) {
        String hashPassword = passwordEncoder.encode(postmanUser.getPassword());
        postmanUser.setPassword(hashPassword);
        User user = this.userService.handleCreateUser(postmanUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        if (id > 1500)
            throw new IdInvalidException("Id không lớn hơn 1500");
        this.userService.deleteUserById(id);
        return ResponseEntity.ok("done");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.fetchUserById(id);
        if (user.isPresent())
            return ResponseEntity.ok(user.get());
        return null;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = this.userService.fetchAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User postmanUser) {
        User user = this.userService.handleUpdateUser(postmanUser);
        return ResponseEntity.ok(user);
    }



}
