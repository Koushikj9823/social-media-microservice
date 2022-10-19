package com.socialmediamicroservice.userservice.repository;

import com.socialmediamicroservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
