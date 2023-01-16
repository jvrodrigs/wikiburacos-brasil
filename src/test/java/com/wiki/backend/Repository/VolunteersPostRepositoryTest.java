package com.wiki.backend.Repository;

import com.wiki.backend.Model.Address;
import com.wiki.backend.Model.Volunteers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class VolunteersPostRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    VolunteersRepository repository;

    @Test
    @DisplayName("Create a Post by Volunter")
    public void testCreateNewPostByVolunteers(){
        Volunteers post = createNewPostVolunteersTest();

        Volunteers postCreated = repository.save(post);

        assertThat(postCreated.getId()).isNotNull();
    }

    public Volunteers createNewPostVolunteersTest(){
        Volunteers post = new Volunteers();

        post.setName("João");
        post.setEmail("jvrodrigs@gmail.com");
        post.setPhone("85938228322");
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
}
