package com.socialmediamicroservice.feedservice.controller;

import com.socialmediamicroservice.feedservice.entity.Feed;
import com.socialmediamicroservice.feedservice.entity.Like;
import com.socialmediamicroservice.feedservice.entity.Post;
import com.socialmediamicroservice.feedservice.entity.User;
import com.socialmediamicroservice.feedservice.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/feed")
public class FeedController {

    @Autowired
    FeedService feedService;
    @GetMapping("/")
    public ResponseEntity<List<Feed>> getAllFeeds() throws IOException {
        return ResponseEntity.ok(feedService.getAllFeeds());
    }

}
