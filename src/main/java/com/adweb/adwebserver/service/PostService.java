package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    Post addComment(int studentID, int postID, String text);
    List<Post> allPost(int courseID);
    Post showPost(int taskID);
    int clap(int taskID);
}
