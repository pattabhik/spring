package com.app.blog.controllers;

import com.app.blog.dto.PostDTO;
import com.app.blog.dto.UpdatePostDTO;
import com.app.blog.models.Posts;
import com.app.blog.models.Users;
import com.app.blog.repository.PostRepository;
import com.app.blog.repository.UserRepository;
import com.app.blog.util.EntitiyHawk;
import com.app.blog.util.JWTUtils;
import com.app.blog.util.PostMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author 1460344
 */
@RestController
@RequestMapping("/api")
public class GlobalController extends EntitiyHawk {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/publish")
    public ResponseEntity publish(@RequestBody PostDTO postDTO, @RequestHeader("Authorization") String token) {
        boolean isValid = isTokenValid(token);
        Object response = null;
        if (!isValid) {
            return new ResponseEntity("Unable to read JSON value", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (postDTO.getBody() == null) {
            response = "body should not be empty";
        } else {
            Posts publish = new Posts();

            publish.setPostTitle(postDTO.getTitle());
            publish.setPostBody(postDTO.getBody());
            publish.setPublishedBy(getUserByToken(token));
            publish.setCreatedOn(new Date());
            publish.setUpdatedOn(null);
            publish.setIsDeleted(false);

            Posts savedPost = postRepository.save(publish);
            response = "Published";
        }
        return genericSuccess(response);
    }

    @GetMapping("/getPost")
    public ResponseEntity getPost() {
        return genericSuccess(postRepository.findAll().stream().map(post -> {
            return new PostMapper().postDetailsToMap(post);
        }).collect(Collectors.toList()));
    }

    @GetMapping("getPostCount")
    public ResponseEntity getPostCount(@RequestHeader("Authorization") String token) {
        boolean isValid = isTokenValid(token);
        if (!isValid) {
            return new ResponseEntity("Unable to read JSON value", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return genericSuccess(postRepository.count());
    }

    @GetMapping("/getPostByUser/{userId}")
    public ResponseEntity getPostByUserId(@PathVariable Integer userId) {
        Object response = null;
        List<Posts> postsByUser = postRepository.findAll().stream().filter(post -> post.getPublishedBy().getUserId() == userId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(postsByUser)) {
            response = "No posts by user Id " + userId;
        } else {
            response = postsByUser.stream().map(post -> {
                return new PostMapper().postDetailsToMap(post);
            }).collect(Collectors.toList());
        }
        return genericSuccess(response);
    }

    @GetMapping("getPost/{postID}")
    public ResponseEntity getPostById(@PathVariable Integer postID) {
        Optional<Posts> post = postRepository.findById(postID);
        Object response = null;
        if (post.isPresent() && !post.get().getIsDeleted()) {
            response = new PostMapper().postDetailsToMap(post.get());
        }else{
            response = "Post Not Found";
        }
        return genericSuccess(response);
    }

    @PostMapping("/updatePost")
    public ResponseEntity updatePost(@RequestBody UpdatePostDTO postDTO) {
        Object response = null;
        Posts postedPost = postRepository.findById(postDTO.getPost_id()).orElse(null);
        if (postedPost != null) {
            postedPost.setPostTitle(postDTO.getTitle());
            postedPost.setPostBody(postDTO.getBody());
            postedPost.setUpdatedOn(new Date());
            postRepository.save(postedPost);
            response = "Post updated";
        }
        return genericSuccess(response);
    }

    @GetMapping("/deletePost/{postID}")
    public ResponseEntity deletePostById(@PathVariable Integer postID) {
        Posts post = postRepository.findById(postID).orElse(null);
        Object response = null;
        if (post == null) {
            response = "Post Not Found";
        } else {
            post.setIsDeleted(true);
            post.setUpdatedOn(new Date());
            postRepository.saveAndFlush(post);
            response = "Post Deleted";
        }
        return genericSuccess(response);
    }

    private boolean isTokenValid(String token) {
        return jwtUtils.validateToken(getToken(token));
    }

    private String getClaimValue(Claims claims, String claimKey) {
        return (claims != null) ? claims.get(claimKey).toString() : null;
    }

    private Users getUserByToken(String token) {
        Claims claims = jwtUtils.getClaims(getToken(token));
        if (claims != null) {
            Optional<Users> user = userRepository.findById(Integer.valueOf(getClaimValue(claims, "user_id")));
            return user.orElse(null);
        }
        return null;
    }

    private String getToken(String token) {
        token = token.replace("Bearer ", "");
        return token;
    }
}
