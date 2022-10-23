package com.socialmediamicroservice.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socialmediamicroservice.notificationservice.pojo.Follow;
import com.socialmediamicroservice.notificationservice.pojo.Post;
import com.socialmediamicroservice.notificationservice.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmailServiceImpl {
    @Autowired
    MailSender mailSender;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${url.user-service}")
    private String userService;
    @Value("${mailSender.username}")
    private String from;

    public void sendMessage(Post post) {
        List<String> userEmails = getUserFollowersEmail(post);
        if (!CollectionUtils.isEmpty(userEmails)) {
            MimeMessage mimeMessage = mailSender.getJavaMailSender().createMimeMessage();
            MimeMessageHelper mimeMessageHelper;
            try {
                mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
                mimeMessageHelper.setFrom(from);
                mimeMessageHelper.setTo(userEmails.toArray(String[]::new));
                String userName = getUserName(post);
                mimeMessageHelper.setSubject(userName + " posted in social-media-microservice");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<html><body><h1>Post Description: ");
                stringBuilder.append(post.getDescription());
                stringBuilder.append("</h1>");
                stringBuilder.append("<h2>Posted by: ").append(userName).append("</h2>");
                stringBuilder.append("<h3>Join Social Media Microservice now!</h3>");
                stringBuilder.append("</body>").append("</html>");
                mimeMessage.setContent(stringBuilder.toString(),"text/html");
                mimeMessageHelper.setText(stringBuilder.toString());
                mailSender.getJavaMailSender().send(mimeMessage);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private String getUserName(Post post) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).convertValue(restTemplate.getForObject(userService + "/v1/user/" + post.getUserId(), User.class), User.class);
        return user.getFirstName();
    }

    private List<String> getUserFollowersEmail(Post post) {
        List<String> emails = new ArrayList<>();
        List<String> users = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> followers = objectMapper.findAndRegisterModules().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).convertValue(restTemplate.getForObject(userService + "/v1/user/" + post.getUserId() + "/followers", List.class), List.class);
        if (!CollectionUtils.isEmpty(followers)) {
            followers.forEach(follow -> {
                Follow followObject = objectMapper.findAndRegisterModules().registerModule(new JavaTimeModule()).convertValue(follow,Follow.class);
                users.add(String.valueOf(followObject.getFollowerId()));
            });
        }
        if (!CollectionUtils.isEmpty(users)) {
            users.forEach(userId -> {
                User follower = objectMapper.registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).convertValue(restTemplate.getForObject(userService + "/v1/user/" + userId, User.class), User.class);
                emails.add(follower.getEmail());
            });
        }
        return emails;
    }
}
