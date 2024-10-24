package vn.tdat.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.tdat.jobhunter.domain.User;
import vn.tdat.jobhunter.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user/create")
    public User getUser(@RequestBody User postmanUser) {
        User user =  this.userService.handleCreateUser(postmanUser);
        return user;
    }
}
