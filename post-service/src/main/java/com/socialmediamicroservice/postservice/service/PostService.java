package com.socialmediamicroservice.postservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socialmediamicroservice.postservice.entity.Post;
import com.socialmediamicroservice.postservice.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    RestTemplate restTemplate;
    @Value("${url.like-service}")
    private String likeService;

    @Autowired
    private KafkaMessageSender kafkaMessageSender;

    public Post getPostById(Integer id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByUser(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    public Post createPost(Post post) {
        post.setTimestamp(LocalDateTime.now());
        postRepository.save(post);
        ObjectWriter objectWriter = new ObjectMapper().
                                        registerModule(new JavaTimeModule()).
                                        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).
                                        writer().withDefaultPrettyPrinter();
        try {
            kafkaMessageSender.sendMessage(objectWriter.writeValueAsString(post));
        } catch (JsonProcessingException jsonProcessingException) {
            log.error(String.valueOf(jsonProcessingException));
        }
        return post;
    }

    public boolean deletePost(Integer postId) {
        if (postRepository.findById(postId).isPresent()) {
            postRepository.deleteById(postId);
            restTemplate.delete(likeService + "/" + postId);
            return true;
        }
        return false;
    }
}
