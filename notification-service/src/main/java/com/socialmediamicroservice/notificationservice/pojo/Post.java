package com.socialmediamicroservice.notificationservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    private Integer postId;
    private Integer userId;
    private String description;
    private LocalDateTime timestamp;
}
