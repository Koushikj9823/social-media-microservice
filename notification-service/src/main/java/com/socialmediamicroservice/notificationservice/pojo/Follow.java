package com.socialmediamicroservice.notificationservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow implements Serializable {
    private Integer followId;
    private Integer userId;
    private Integer followerId;
    private LocalDateTime timestamp;
}
