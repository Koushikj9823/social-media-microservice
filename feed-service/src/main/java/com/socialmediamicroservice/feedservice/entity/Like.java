package com.socialmediamicroservice.feedservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like implements Serializable {
    private Integer likeId;
    private Integer userId;
    private Integer postId;
    private LocalDateTime timestamp;
}
