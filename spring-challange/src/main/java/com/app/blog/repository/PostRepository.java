package com.app.blog.repository;

import com.app.blog.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author 1460344
 */
@Repository
public interface PostRepository extends JpaRepository<Posts, Integer> {
    //@Query("SELECT p FROM Posts p WHERE p.published_by=:userId")
    //List<Posts> findAllByUserId(Integer userId);

    //@Query("SELECT p FROM Posts p WHERE p.post_id=:postId and p.published_by=:userId")
    //Posts findByPostIdByUserId(Integer postId,Integer userId);

    //@Query("SELECT p FROM Posts p WHERE p.post_title=:postTitle and p.published_by=:userId")
    //Posts findByPostTitleByUserId(String postTitle, Integer userId);
}
