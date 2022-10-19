package com.socialmediamicroservice.feedservice.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmediamicroservice.feedservice.entity.Feed;
import com.socialmediamicroservice.feedservice.entity.Like;
import com.socialmediamicroservice.feedservice.entity.Post;
import com.socialmediamicroservice.feedservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FeedService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${url.post-service}")
    private String postServiceUrl;
    @Value("${url.user-service}")
    private String userService;
    @Value("${url.like-service}")
    private String likeService;

    public List<Feed> getAllFeeds() throws IOException {
        List<Feed> feeds = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> posts = restTemplate.getForObject(postServiceUrl + "/v1/post/", List.class);

        log.info("Posts: {}",posts);
        if (posts != null && !posts.isEmpty()) {
            log.info("Posts: {}",posts);
            posts.forEach(postObject -> {
                Post post = objectMapper.findAndRegisterModules().convertValue(postObject,Post.class);
                Feed feed = new Feed();
                User user = objectMapper.findAndRegisterModules().convertValue(restTemplate.getForObject(userService + "/v1/user/" + post.getUserId(), User.class),User.class);
                List<Like> likes = objectMapper.findAndRegisterModules().convertValue(restTemplate.getForObject(likeService + "/v1/like/" + post.getPostId(), List.class),List.class);
                feed.setPost(post);
                feed.setUser(user);
                feed.setLikes(likes);
                feeds.add(feed);
            });
        }
        return feeds;
    }
}
