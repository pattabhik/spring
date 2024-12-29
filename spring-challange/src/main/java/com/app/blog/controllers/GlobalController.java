package com.app.blog.controllers;

import com.app.blog.dto.PostDTO;
import com.app.blog.models.Posts;
import com.app.blog.repository.PostRepository;
import com.app.blog.repository.UserRepository;
import com.app.blog.util.EntitiyHawk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/publish")
    public void publish(@RequestBody PostDTO postDTO) {
        Posts publish = new Posts();

        publish.setPostTitle(postDTO.getTitle());
        publish.setPostBody(postDTO.getBody());
        publish.setPublishedBy(null);//TODO
        publish.setCreatedOn(new Date());
        publish.setUpdatedOn(null);
        publish.setIsDeleted(false);

        Posts savedPost = postRepository.save(publish);
    }

    @GetMapping("/getPost")
    public ResponseEntity getPost() {
        return genericSuccess(postRepository.findAll());
    }

    @GetMapping("getPostCount")
    public ResponseEntity getPostCount() {
        return genericSuccess(postRepository.count());
    }

    @GetMapping("/getPostByUser/{userId}")
    public List<Posts> getPostByUserId(@PathVariable Integer userId) {
        List<Posts> postsListByUser =null;// postRepository.findAllByUserId(userId);
        return postsListByUser;
    }

    @PostMapping("/updatePost")
    public void updatePost(@RequestBody PostDTO postDTO) {
        Posts postedPost = null;//postRepository.findByPostTitleByUserId(postDTO.getTitle(), 1);
        if (postedPost != null) {
            postedPost.setPostTitle(postDTO.getTitle());
            postedPost.setPostBody(postDTO.getBody());
            postedPost.setUpdatedOn(new Date());
            postedPost.setIsDeleted(false);
            postRepository.save(postedPost);
        }
    }

    @GetMapping("getPost/{postID}")
    public Posts getPostById(@PathVariable Integer postID) {
        Optional<Posts> post = postRepository.findById(postID);
        return post.isPresent() ? post.get() : null;
    }

    @GetMapping("/deletePost/{postID}")
    public void deletePostById(@PathVariable Integer postID) {
        //postRepository.delete(postRepository.findByPostIdByUserId(postID, 1));
    }
}
