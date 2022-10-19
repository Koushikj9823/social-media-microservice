package com.socialmediamicroservice.postservice.controller;

import com.socialmediamicroservice.postservice.entity.Post;
import com.socialmediamicroservice.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/{postId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPost(@PathVariable String postId){
        return ResponseEntity.ok(postService.getPostById(Integer.valueOf(postId)));
    }
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping(value = "/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPostByUserId(@PathVariable String userId){
        return ResponseEntity.ok(postService.getPostsByUser(Integer.valueOf(userId)));
    }

    @PostMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> persistPost(@RequestBody Post post){
        return ResponseEntity.ok(postService.createPost(post));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable String postId){
        return ResponseEntity.ok(postService.deletePost(Integer.valueOf(postId)));
    }
}
