package com.socialmediamanager.likeservice.controller;

import com.socialmediamanager.likeservice.entity.Like;
import com.socialmediamanager.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/like")
public class LikeController {

    @Autowired
    LikeService likeService;
    @RequestMapping("/{postId}")
    public ResponseEntity<List<Like>> getLikesForThePost(@PathVariable String postId){
        return ResponseEntity.ok(likeService.getAllLikesForThePost(postId));
    }

    @PostMapping("/")
    public ResponseEntity<Like> postLike(@RequestBody Like like){
        return ResponseEntity.ok(likeService.addLike(like));
    }
    @PostMapping("/{likeId}")
    public ResponseEntity<Boolean> deleteLike(@PathVariable String likeId){
        return ResponseEntity.ok(likeService.deleteLike(likeId));
    }
}
