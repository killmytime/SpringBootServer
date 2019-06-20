package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Post;
import com.adweb.adwebserver.domain.PostNode;
import com.adweb.adwebserver.domain.Student;
import com.adweb.adwebserver.domain.repository.PostRepository;
import com.adweb.adwebserver.service.PostService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post addComment( int postID, String text, Student student) {
        PostNode postNode = new PostNode(student.getName(),student.getAvatar(),text);
        Post post0 = postRepository.getPostByPostId(postID);
        JSONArray textList = post0.getText();
        textList.add(JSONObject.toJSON(postNode));
        post0.setText(textList);
        postRepository.save(post0);
        return post0;
    }


    @Override
    public Post showPost(int postID) {
        return postRepository.getPostByPostId(postID);
    }

    @Override
    public int clap(int postID) {
        Post post0 = postRepository.getPostByPostId(postID);
        int clap = post0.getClap();
        post0.setClap(++clap);
        postRepository.save(post0);
        return clap;
    }

    @Override
    public List<Post> myPost(int studentID) {
        return postRepository.getPostByStudentId(studentID);
    }

    @Override
    public Post deletePost(int studentID, int postID) {
        return postRepository.removePostByStudentIdAndPostId(studentID, postID);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

}
