package com.wiki.backend.security.config;

import com.wiki.backend.Exceptions.CustomException;
import com.wiki.backend.Model.User;
import com.wiki.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService  implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public UserDetails isAuthentication(User user){
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        boolean isPasswordMatcher = encoder.matches(user.getPassword(), userDetails.getPassword());
        if (isPasswordMatcher) return userDetails;
        throw new CustomException("Password is invalid.");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userIsAuth = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found in database."));

        String[] roles = userIsAuth.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User.builder()
                .username(userIsAuth.getUsername())
                .password(userIsAuth.getPassword())
                .roles(roles)
                .build();
    }
}
