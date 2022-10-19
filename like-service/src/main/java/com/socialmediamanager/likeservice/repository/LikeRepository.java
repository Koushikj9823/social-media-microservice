package com.socialmediamanager.likeservice.repository;

import com.socialmediamanager.likeservice.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Integer> {
    List<Like> findByPostId(Integer postId);
}
