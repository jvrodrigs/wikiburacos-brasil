package com.wiki.backend.Repository;

import com.wiki.backend.Model.Volunteers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VolunteersRepository extends JpaRepository<Volunteers, Long> {
}
