package com.socialmediamicroservice.feedservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer followerId;
    private LocalDateTime timestamp;
}
