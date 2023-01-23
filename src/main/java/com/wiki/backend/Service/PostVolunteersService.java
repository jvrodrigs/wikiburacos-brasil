package com.wiki.backend.Service;

import com.wiki.backend.Exceptions.CustomException;
import com.wiki.backend.Model.DTO.VolunteersDTO;
import com.wiki.backend.Model.PostShare;
import com.wiki.backend.Model.ResultPagination;
import com.wiki.backend.Model.Volunteers;
import com.wiki.backend.Repository.PostShareRepository;
import com.wiki.backend.Repository.VolunteersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostVolunteersService {
    @Autowired
    private VolunteersRepository repository;
    @Autowired
    private PostShareRepository postShareRepository;
    @Value("${wiki.url}") String URL;

    public ResultPagination<Volunteers> findAllPagination(Volunteers filter, Pageable pageable){
        Example<Volunteers> ex = Example.of(filter,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );

        Page<Volunteers> result = repository.findAll(ex, pageable);

        List<Volunteers> transfToList = result.getContent().stream().collect(Collectors.toList());


        return convertToPageToDto(transfToList, result.getNumber(), result.getTotalElements(), result.getTotalPages());
    }

    @Transactional
    public VolunteersDTO create(Volunteers vl) {
        Volunteers newPost = repository.save(vl);
        PostShare share = new PostShare();

        UUID shareID = UUID.randomUUID();

        share.setShare(shareID.toString());
        share.setPostId(newPost.getId().toString());
        share.setLinkShare(URL + shareID.toString());

        PostShare shareCreated = postShareRepository.save(share);
        return convertEntityToDto(vl, shareCreated);
    }

    public VolunteersDTO getPostById(Long postId) {
        Volunteers post = repository.findById(postId)
                .orElseThrow( () -> new CustomException("Post ID invalid or not  found."));

        PostShare shareIsExist = postShareRepository.findPostShareByPostId(post.getId().toString())
                .orElseThrow( () -> new CustomException("There is no share link for this post."));

        return convertEntityToDto(post, shareIsExist);
    }

    public VolunteersDTO getPostByLinkShare(String share){
        PostShare shareIsExist = postShareRepository.findPostShareByShare(share)
                .orElseThrow( () -> new CustomException("Share ID invalid or not found."));

        Volunteers post = repository.findById(Long.parseLong(shareIsExist.getPostId()))
                .orElseThrow( () -> new CustomException("There is no post for this share link."));

        return convertEntityToDto(post, shareIsExist);
    }

    public ResultPagination<Volunteers> convertToPageToDto(List<Volunteers> data, int currentPage, Long totalElements, int totalPages){
        ResultPagination<Volunteers> pagination = new ResultPagination<>();

        pagination.setContent(data);
        pagination.setCurrentPage(currentPage);
        pagination.setTotalElements(totalElements);
        pagination.setTotalPage(totalPages);

        return pagination;
    }


    public VolunteersDTO convertEntityToDto(Volunteers vl, PostShare share){
        VolunteersDTO dto = new VolunteersDTO();
        dto.setPost(vl);
        dto.setShare(share);

        return dto;
    }
}
