package com.wiki.backend.Repository;

import com.wiki.backend.Model.PostShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostShareRepository extends JpaRepository<PostShare, Long> {

    Optional<PostShare> findPostShareByShare(String share);
}
