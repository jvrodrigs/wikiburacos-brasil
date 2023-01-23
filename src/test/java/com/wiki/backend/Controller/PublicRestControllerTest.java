package com.wiki.backend.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiki.backend.Model.Address;
import com.wiki.backend.Model.DTO.VolunteersDTO;
import com.wiki.backend.Model.PostShare;
import com.wiki.backend.Model.Volunteers;
import com.wiki.backend.Service.PostVolunteersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PublicRestControllerTest {
    @MockBean
    PostVolunteersService service;
    @Autowired
    MockMvc mvc;
    static String URL_FIX = "http://localhost:8080/public-post/share/";
    private static final String URL = "/public-post";

    @Test
    public void testSaveNewPost() throws Exception {
        BDDMockito.given(service.create(Mockito.any(Volunteers.class))).willReturn(getPostItem());

        mvc.perform(MockMvcRequestBuilders.post(URL + "/create").content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.post.id").value(11L))
                .andExpect(jsonPath("$.data.post.name").value("João"))
                .andExpect(jsonPath("$.data.post.email").value("jvrodrigs@gmail.com"))
                .andExpect(jsonPath("$.data.post.phone").value("85938228322"))
                .andExpect(jsonPath("$.data.post.address.street").value("Rua Pedestre C"))
                .andExpect(jsonPath("$.data.post.address.neighborhood").value("Parque"))
                .andExpect(jsonPath("$.data.post.address.city").value("Caucaia"))
                .andExpect(jsonPath("$.data.post.address.state").value("Ceará"))
                .andExpect(jsonPath("$.data.post.address.number").value("66"))
                .andExpect(jsonPath("$.data.post.address.cep").value("61648030"))
                .andExpect(jsonPath("$.data.post.photo").exists())
                .andExpect(jsonPath("$.data.post.description").exists())
                .andExpect(jsonPath("$.data.share.id").value(11L))
                .andExpect(jsonPath("$.data.share.share").value("8115fa99-26f9-45df-bac1-5d46b3f638fa"))
                .andExpect(jsonPath("$.data.share.postId").value("11"))
                .andExpect(jsonPath("$.data.share.linkShare").value(URL_FIX + "8115fa99-26f9-45df-bac1-5d46b3f638fa"));
    }

    @Test
    public void testGetPostById() throws Exception{
        BDDMockito.given(service.getPostById(Mockito.anyLong())).willReturn(getPostItem());

        mvc.perform(MockMvcRequestBuilders.get(URL + "/11").content(getJsonPayload())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.post.id").value(11L))
                .andExpect(jsonPath("$.data.post.name").value("João"))
                .andExpect(jsonPath("$.data.post.email").value("jvrodrigs@gmail.com"))
                .andExpect(jsonPath("$.data.post.phone").value("85938228322"))
                .andExpect(jsonPath("$.data.post.address.street").value("Rua Pedestre C"))
                .andExpect(jsonPath("$.data.post.address.neighborhood").value("Parque"))
                .andExpect(jsonPath("$.data.post.address.city").value("Caucaia"))
                .andExpect(jsonPath("$.data.post.address.state").value("Ceará"))
                .andExpect(jsonPath("$.data.post.address.number").value("66"))
                .andExpect(jsonPath("$.data.post.address.cep").value("61648030"))
                .andExpect(jsonPath("$.data.post.photo").exists())
                .andExpect(jsonPath("$.data.post.description").exists())
                .andExpect(jsonPath("$.data.share.id").value(11L))
                .andExpect(jsonPath("$.data.share.share").value("8115fa99-26f9-45df-bac1-5d46b3f638fa"))
                .andExpect(jsonPath("$.data.share.postId").value("11"))
                .andExpect(jsonPath("$.data.share.linkShare").value(URL_FIX + "8115fa99-26f9-45df-bac1-5d46b3f638fa"));
    }

    @Test
    public void testGetPostByShareLink() throws Exception{
        BDDMockito.given(service.getPostByLinkShare(Mockito.anyString())).willReturn(getPostItem());

        mvc.perform(MockMvcRequestBuilders.get(URL + "/share/" + "8115fa99-26f9-45df-bac1-5d46b3f638fa").content(getJsonPayload())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.post.id").value(11L))
                .andExpect(jsonPath("$.data.post.name").value("João"))
                .andExpect(jsonPath("$.data.post.email").value("jvrodrigs@gmail.com"))
                .andExpect(jsonPath("$.data.post.phone").value("85938228322"))
                .andExpect(jsonPath("$.data.post.address.street").value("Rua Pedestre C"))
                .andExpect(jsonPath("$.data.post.address.neighborhood").value("Parque"))
                .andExpect(jsonPath("$.data.post.address.city").value("Caucaia"))
                .andExpect(jsonPath("$.data.post.address.state").value("Ceará"))
                .andExpect(jsonPath("$.data.post.address.number").value("66"))
                .andExpect(jsonPath("$.data.post.address.cep").value("61648030"))
                .andExpect(jsonPath("$.data.post.photo").exists())
                .andExpect(jsonPath("$.data.post.description").exists())
                .andExpect(jsonPath("$.data.share.id").value(11L))
                .andExpect(jsonPath("$.data.share.share").value("8115fa99-26f9-45df-bac1-5d46b3f638fa"))
                .andExpect(jsonPath("$.data.share.postId").value("11"))
                .andExpect(jsonPath("$.data.share.linkShare").value(URL_FIX + "8115fa99-26f9-45df-bac1-5d46b3f638fa"));
    }

    @Test
    public void testInvalidNewPost() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post(URL + "/create").content(getJsonPayloadInvalid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors[0]").value("Phone field is required"));;
    }

    @Test
    public void testInvalidGetPostById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors[0]").value("Post ID invalid or not  found."));
    }

    @Test
    public void testInvalidGetPostByShareLink() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL + "/share/8115fa99-26f9-45df-bac1-5d46b3f638f")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors[0]").value(	"Share ID invalid or not found."));
    }


    public Volunteers createNewPostVolunteersTest(){
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
    public Volunteers createNewPostVolunteersInvalidTest(){
        Volunteers post = new Volunteers();

        post.setId(11L);
        post.setName("João");
        post.setEmail("jvrodrigs@gmail.com");
        post.setAddress(createAddres());
        post.setPhoto("https://s3.region-code.amazonaws.com/bucket-name/key-name");
        post.setDescription("Está é uma descrição do teste");

        return post;
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
    public PostShare createNewPostShareTest(){
        PostShare shared = new PostShare();
        shared.setId(11L);
        shared.setShare("8115fa99-26f9-45df-bac1-5d46b3f638fa");
        shared.setPostId("11");
        shared.setLinkShare(URL_FIX + "8115fa99-26f9-45df-bac1-5d46b3f638fa");
        shared.setCreatedAt(new Date());
        return shared;
    }
    private String getJsonPayload() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(createNewPostVolunteersTest());
    }
    private String getJsonPayloadInvalid() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(createNewPostVolunteersInvalidTest());
    }
    private VolunteersDTO getPostItem() throws JsonProcessingException{
        VolunteersDTO dto = new VolunteersDTO();

        dto.setPost(createNewPostVolunteersTest());
        dto.setShare(createNewPostShareTest());

        return dto;
    }
}
