package com.socialmediamanager.likeservice.service;

import com.socialmediamanager.likeservice.entity.Like;
import com.socialmediamanager.likeservice.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;
    public List<Like> getAllLikesForThePost(String postId){
        return likeRepository.findByPostId(Integer.valueOf(postId));
    }

    public Like addLike(Like like){
        like.setTimestamp(LocalDateTime.now());
        likeRepository.save(like);
        return like;
    }

    public boolean deleteLike(String likeId){
        if(likeRepository.findById(Integer.valueOf(likeId)).isPresent()) {
            likeRepository.deleteById(Integer.valueOf(likeId));
            return true;
        }
        else
            return false;
    }
}
