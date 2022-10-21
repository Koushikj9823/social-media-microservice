package com.socialmediamicroservice.userservice.repository;

import com.socialmediamicroservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
}
