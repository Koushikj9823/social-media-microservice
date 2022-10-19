package com.socialmediamicroservice.feedservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feed implements Serializable {
    private Post post;
    private User user;
    private List<Like> likes;
}
