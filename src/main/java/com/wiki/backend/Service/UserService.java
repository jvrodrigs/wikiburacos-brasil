package com.wiki.backend.Service;

import com.wiki.backend.Exceptions.CustomException;
import com.wiki.backend.Model.DTO.UserDTO;
import com.wiki.backend.Model.ResultPagination;
import com.wiki.backend.Model.User;
import com.wiki.backend.Model.Volunteers;
import com.wiki.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResultPagination<UserDTO> listToUsers(User userFilter, Pageable pg){
        Example<User> ex = Example.of(userFilter,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withIgnorePaths("admin")
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );

        Page<User> res = repository.findAll(ex, pg);

        List<UserDTO> transfToList = res.getContent().stream()
                .map(this::convertEntityToDto).collect(Collectors.toList());

        return convertToPageToDto(transfToList, res.getNumber(), res.getTotalElements(), res.getTotalPages());
    }

    public UserDTO getLoginUser(){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            User isExists = repository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new CustomException("Error when trying to locate user session."));
            return convertEntityToDto(isExists);
        } catch (Exception e){
            throw new CustomException("Error in session.");
        }
    }

    public User convertDtoToEntity(UserDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return user;
    }

    public UserDTO convertEntityToDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setAdmin(entity.isAdmin());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public ResultPagination<UserDTO> convertToPageToDto(List<UserDTO> data, int currentPage, Long totalElements, int totalPages){
        ResultPagination<UserDTO> pagination = new ResultPagination<>();

        pagination.setContent(data);
        pagination.setCurrentPage(currentPage);
        pagination.setTotalElements(totalElements);
        pagination.setTotalPage(totalPages);

        return pagination;
    }
}
