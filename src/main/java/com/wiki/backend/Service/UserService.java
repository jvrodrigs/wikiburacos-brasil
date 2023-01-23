package com.wiki.backend.Service;

import com.wiki.backend.Exceptions.CustomException;
import com.wiki.backend.Model.User;
import com.wiki.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User create(User user){
        if (repository.findByUsernameEquals(user.getUsername()).isPresent()){
            throw new CustomException("This username already exists in our system");
        }

        if (repository.findByEmailEquals(user.getEmail()).isPresent()){
            throw new CustomException("This email already exists in our system");
        }

        return repository.save(user);
    }

    public User getLoginUser(){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            return repository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new CustomException("Error when trying to locate user session."));
        } catch (Exception e){
            throw new CustomException("Error in session.");
        }
    }
}
