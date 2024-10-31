package vn.tdat.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.tdat.jobhunter.domain.User;
import vn.tdat.jobhunter.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user/create")
    public User getUser(@RequestBody User postmanUser) {
        User user = this.userService.handleCreateUser(postmanUser);
        return user;
    }
    
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id)
    {
        this.userService.deleteUserById(id);
        return "done";
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.fetchUserById(id);
        if (user.isPresent())
            return user.get();
        return null;
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        return this.userService.fetchAllUser();
    }

    @PostMapping("/user/update/{id}")
    public User updateUser(@RequestBody User postmanUser, @PathVariable("id") long id) {
        User user = this.userService.handleUpdateUser(id, postmanUser);
        return user;
    }
    
}
