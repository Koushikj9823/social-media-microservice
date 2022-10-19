package com.socialmediamicroservice.postservice.service;

import com.socialmediamicroservice.postservice.entity.Post;
import com.socialmediamicroservice.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Value("${url.like-service}")
    private String likeService;

    @Autowired
    RestTemplate restTemplate;

    public Post getPostById(Integer id){
        Optional<Post> postOptional =  postRepository.findById(id);
        return postOptional.orElse(null);
    }
    public List<Post> getAllPosts(){
        List<Post> posts =  postRepository.findAll();
        return posts;
    }
    public List<Post> getPostsByUser(Integer userId){
        return postRepository.findPostByUserId(userId);
    }

    public Post createPost(Post post){
        post.setTimestamp(LocalDateTime.now());
        postRepository.save(post);
        return post;
    }

    public boolean deletePost(Integer postId){
        if(postRepository.findById(postId).isPresent()) {
            postRepository.deleteById(postId);
            restTemplate.delete(likeService+"/"+postId);
            return true;
        }
        return false;
    }
}
