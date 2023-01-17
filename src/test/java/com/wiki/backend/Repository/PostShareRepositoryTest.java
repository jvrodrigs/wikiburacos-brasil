package com.wiki.backend.Repository;

import com.wiki.backend.Model.PostShare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class PostShareRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PostShareRepository repository;

    static String URL_FIX = "http://localhost:8080/public-post/share/";

    @Test
    @DisplayName("Save to PostShare")
    public void testSavePostShare() {
        PostShare share = createNewPostShareTest();

        PostShare saveShare = repository.save(share);

        assertThat(saveShare.getId()).isNotNull();
    }

    @Test
    @DisplayName("Get to SHARE")
    public void testGetPostByShareId(){
        PostShare share = createNewPostShareTest();
        testEntityManager.persist(share);

        boolean exists = repository.findPostShareByShare(share.getShare()).isPresent();

        assertThat(exists).isTrue();
    }

    public static PostShare createNewPostShareTest(){
        PostShare shared = new PostShare();

        UUID sharePostID = UUID.randomUUID();

        shared.setShare(sharePostID.toString());
        shared.setPostId("1");
        shared.setLinkShare(URL_FIX + sharePostID.toString());
        shared.setCreatedAt(new Date());
        return shared;
    }
}
