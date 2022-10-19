package com.socialmediamicroservice.feedservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    private Integer postId;
    private Integer userId;
    private String description;
    private LocalDateTime timestamp;
}
