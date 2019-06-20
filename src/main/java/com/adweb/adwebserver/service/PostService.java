package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Post;
import com.adweb.adwebserver.domain.Student;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    Post addComment(int postID, String text, Student student);
    Post showPost(int postID);
    int clap(int postID);
    List<Post> myPost(int studentID);
    Post deletePost(int studentID, int postID);
    List<Post> getAllPost();
}
