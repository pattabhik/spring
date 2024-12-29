package com.app.blog.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class UpdatePostDTO {
    int post_id;
    @NotBlank(message = "should not be empty")
    @Length(max = 450)
    @Valid
    String title;
    @NotBlank(message = "should not be empty")
    @Length(max = 5000)
    @Valid
    String body;

    @Override
    public String toString() {
        return "UpdatePostDTO{" +
                "post_id=" + post_id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
