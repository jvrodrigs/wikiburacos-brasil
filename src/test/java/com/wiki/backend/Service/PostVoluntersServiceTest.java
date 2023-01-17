package com.wiki.backend.Service;

import com.wiki.backend.Model.Address;
import com.wiki.backend.Model.DTO.VolunteersDTO;
import com.wiki.backend.Model.PostShare;
import com.wiki.backend.Model.Volunteers;
import com.wiki.backend.Repository.PostShareRepository;
import com.wiki.backend.Repository.VolunteersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class PostVoluntersServiceTest {
    @Autowired
    PostVolunteersService service;
    @Autowired
    PostShareRepository postRepository;
    @MockBean
    VolunteersRepository repository;

    static String URL_FIX = "http://localhost:8080/";

    @Test
    @DisplayName("Save Post in File Service")
    public void testSavePost() {
        Volunteers post = createPostValidTest();

        BDDMockito.when(repository.save(post)).thenReturn(post);

        VolunteersDTO savedPost = service.create(post);

        //TODO: Primeira validação é a Postagem; a próxima é o de compartilhamento;
        assertThat(savedPost.getPost().getId()).isNotNull();
        assertThat(savedPost.getPost().getName()).isEqualTo("João");
        assertThat(savedPost.getPost().getEmail()).isEqualTo("jvrodrigs@gmail.com");
        assertThat(savedPost.getPost().getPhone()).isEqualTo("85938228322");
        assertThat(savedPost.getPost().getPhoto()).isNotNull();

        //TODO: Compartilhamento;
        assertThat(savedPost.getShare().getId()).isNotNull();
        assertThat(savedPost.getShare().getPostId()).isEqualTo(savedPost.getPost().getId().toString());
        assertThat(savedPost.getShare().getLinkShare()).isNotNull();
        assertThat(savedPost.getShare().getShare()).isNotNull();
    }

    private Volunteers createPostValidTest() {
        Volunteers post = new Volunteers();
        post.setId(11L);
        post.setName("João");
        post.setEmail("jvrodrigs@gmail.com");
        post.setPhone("85938228322");
        post.setAddress(createAddres());
        post.setPhoto("https://s3.region-code.amazonaws.com/bucket-name/key-name");
        post.setDescription("Está é uma descrição do teste");

        return post;
    }

    public PostShare createNewPostShareTest(){
        PostShare shared = new PostShare();

        UUID sharePostID = UUID.randomUUID();
        shared.setId(12L);
        shared.setShare(sharePostID.toString());
        shared.setLinkShare(URL_FIX + sharePostID.toString());
        shared.setCreatedAt(new Date());
        return shared;
    }
    public Address createAddres() {
        Address address = new Address();

        address.setStreet("Rua Pedestre C");
        address.setNeighborhood("Parque");
        address.setCity("Caucaia");
        address.setState("Ceará");
        address.setNumber("66");
        address.setCep("61648030");

        return address;
    }
}
