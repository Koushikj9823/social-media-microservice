package com.socialmediamicroservice.userservice.service;

import com.socialmediamicroservice.userservice.entity.Follow;
import com.socialmediamicroservice.userservice.entity.User;
import com.socialmediamicroservice.userservice.repository.FollowRepository;
import com.socialmediamicroservice.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    public User getUserDetails(Integer id){
        Optional<User> userOptional =  userRepository.findById(id);
        return userOptional.orElse(null);
    }
    public List<User> getAllUserDetails(){
        return userRepository.findAll();
    }

    public User postUserDetails(User user){
        user.setTimestamp(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }
    public User updateUserDetails(User user){
        Optional<User> user1 = userRepository.findById(user.getUserId());
        if(user1.isPresent()){
            user1.get().setFirstName(user.getFirstName());
            user1.get().setLastName(user.getLastName());
            user1.get().setEmail(user.getEmail());
            userRepository.save(user1.get());
            return user1.get();
        }
        else return user;
    }
    public List<Follow> getAllFollowersByUserId(Integer userId){
        return followRepository.findByUserId(userId);
    }

    public Follow followUser(Follow follow){
        follow.setTimestamp(LocalDateTime.now());
        followRepository.save(follow);
        return follow;
    }
}
