package com.socialmediamicroservice.userservice.controller;

import com.socialmediamicroservice.userservice.entity.Follow;
import com.socialmediamicroservice.userservice.entity.User;
import com.socialmediamicroservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserDetails(Integer.valueOf(userId)));
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUserInfo(){
        return ResponseEntity.ok(userService.getAllUserDetails());
    }

    @PostMapping("")
    public ResponseEntity<User> persistUserDetails(@RequestBody User user){
        return ResponseEntity.ok(userService.postUserDetails(user));
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<Follow>> getAllFollowersForUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getAllFollowersByUserId(Integer.valueOf(userId)));
    }

    @PostMapping("/follow")
    public ResponseEntity<Follow> followTheUser(@RequestBody Follow follow){
        return ResponseEntity.ok(userService.followUser(follow));
    }
    @PutMapping("")
    public ResponseEntity<User> updateUserDetails(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUserDetails(user));
    }
}
