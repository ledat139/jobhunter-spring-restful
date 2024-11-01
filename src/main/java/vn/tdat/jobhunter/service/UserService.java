package vn.tdat.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.tdat.jobhunter.domain.User;
import vn.tdat.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public Optional<User> fetchUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public List<User> fetchAllUser() {
        return this.userRepository.findAll();
    }

    public User handleUpdateUser(User postmanUser) {
        Optional<User> user = this.userRepository.findById(postmanUser.getId());
        if (user.isPresent()) {
            user.get().setName(postmanUser.getName());
            user.get().setEmail(postmanUser.getEmail());
            user.get().setPassword(postmanUser.getPassword());
            return this.userRepository.save(user.get());
        }
        return null;
    }
}
