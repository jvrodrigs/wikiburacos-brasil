package com.wiki.backend.Controller;

import com.wiki.backend.Model.DTO.VolunteersDTO;
import com.wiki.backend.Model.ResultPagination;
import com.wiki.backend.Model.Volunteers;
import com.wiki.backend.Service.PostVolunteersService;
import com.wiki.backend.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("public-post")
public class PublicRestController {
    @Autowired
    private PostVolunteersService service;

    @GetMapping("/listing")
    public ResponseEntity<ResultPagination<Volunteers>> listToAllPagination(Volunteers filter,
                                                                            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        ResultPagination<Volunteers> response = service.findAllPagination(filter, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<VolunteersDTO>> createToPost(@Validated @RequestBody Volunteers post, BindingResult result){
        Response<VolunteersDTO> res = new Response<>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> res.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        VolunteersDTO postCreated = service.create(post);

        res.setData(postCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
