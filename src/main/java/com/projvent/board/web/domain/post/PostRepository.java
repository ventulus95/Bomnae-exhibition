package com.projvent.board.web.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p FROM  Post p ORDER BY p.Id DESC")
    List<Post> findAllDesc();
}
