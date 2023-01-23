package com.wiki.backend.Controller.Auth;

import com.wiki.backend.Exceptions.PasswordInvalidException;
import com.wiki.backend.Model.DTO.LoginDTO;
import com.wiki.backend.Model.DTO.TokenResponseDTO;
import com.wiki.backend.Model.DTO.UserDTO;
import com.wiki.backend.Model.User;
import com.wiki.backend.Service.UserService;
import com.wiki.backend.Utils.Response;
import com.wiki.backend.security.config.UserAuthService;
import com.wiki.backend.security.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    @Autowired
    private JwtService jwt;
    @Autowired
    private UserAuthService authService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService service;

    @PostMapping
    public TokenResponseDTO auth(@RequestBody LoginDTO credential){
        try {
            User userIsAuth = new User();
            userIsAuth.setUsername(credential.getUsername());
            userIsAuth.setPassword(credential.getPassword());

            UserDetails userDetails = authService.isAuthentication(userIsAuth);

            String tk = jwt.generatedToken(userIsAuth);
            return new TokenResponseDTO(userIsAuth.getPassword(), tk);
        } catch (PasswordInvalidException | UsernameNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

    @PostMapping("user/create")
    public ResponseEntity<Response<UserDTO>> createNewUserApp(@Validated @RequestBody User user, BindingResult result){
        Response<UserDTO> res = new Response<>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> res.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        String BCriptorPassword = encoder.encode(user.getPassword());
        user.setPassword(BCriptorPassword);
        User created = service.create(user);

        res.setData(convertEntityToDto(created));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    private UserDTO convertEntityToDto(User u){
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setName(u.getName());
        dto.setUsername(u.getUsername());

        return dto;
    }

}
