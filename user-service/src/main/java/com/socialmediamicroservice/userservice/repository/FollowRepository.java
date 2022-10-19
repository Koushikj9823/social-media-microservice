package com.socialmediamicroservice.userservice.repository;

import com.socialmediamicroservice.userservice.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByUserId(Integer userId);
}
