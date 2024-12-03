package vn.tdat.jobhunter.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vn.tdat.jobhunter.repository.UserRepository;

import java.util.Collections;

@Component("userDetailsService")
public class UserDetailServiceCustom implements UserDetailsService{
    private final UserRepository userRepository;

    public UserDetailServiceCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        vn.tdat.jobhunter.domain.User user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("Không tìm thấy user");
        return new User(user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
