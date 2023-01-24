package com.wiki.backend.Controller;

import com.wiki.backend.Exceptions.CustomException;
import com.wiki.backend.Model.DTO.UserDTO;
import com.wiki.backend.Model.ResultPagination;
import com.wiki.backend.Model.User;
import com.wiki.backend.Model.Volunteers;
import com.wiki.backend.Service.PostVolunteersService;
import com.wiki.backend.Service.UserService;
import com.wiki.backend.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private PostVolunteersService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public ResponseEntity<ResultPagination<Volunteers>> listAdminPosts(Volunteers filter,
                                                                       @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        UserDTO isAdmin = userService.getLoginUser();

        if (!isAdmin.getAdmin()){
            filter.setName(isAdmin.getName());
            filter.setEmail(isAdmin.getEmail());
        }

        Pageable pageable = PageRequest.of(page, size);

        ResultPagination<Volunteers> response = postService.findAllPagination(filter, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<ResultPagination<UserDTO>> listAdminUsers(User filter,
                                                                    @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        UserDTO isAdmin = userService.getLoginUser();

        if (!isAdmin.getAdmin()){
            throw new CustomException("You do not have permission to access this request.");
        }

        Pageable pageable = PageRequest.of(page, size);

        ResultPagination<UserDTO> response = userService.listToUsers(filter, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Response<String>> deletePostFindAdmin(@PathVariable Long id){
        Response<String> response = new Response<>();

        UserDTO isAdmin = userService.getLoginUser();

        if (!isAdmin.getAdmin()){
            throw new CustomException("You do not have permission to access this request.");
        }

        response.setData("Post successfully removed!");
        return ResponseEntity.ok(response);
    }
}
