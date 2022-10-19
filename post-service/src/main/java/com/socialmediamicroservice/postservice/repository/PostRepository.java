package com.socialmediamicroservice.postservice.repository;

import com.socialmediamicroservice.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findPostByUserId(Integer userId);
}
