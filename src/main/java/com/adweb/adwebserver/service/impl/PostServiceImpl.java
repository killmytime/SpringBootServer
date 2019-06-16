package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Post;
import com.adweb.adwebserver.domain.PostNode;
import com.adweb.adwebserver.domain.repository.PostRepository;
import com.adweb.adwebserver.service.PostService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    //todo 讨论一下这样是否正确
    @Override
    public Post addComment(int studentID, int postID, String text) {
        PostNode postNode = new PostNode(studentID, text);
        Post post0 = postRepository.getPostByPostId(postID);
        JSONArray textList = post0.getText();
        textList.add(JSONObject.toJSON(postNode));
        post0.setText(textList);
        postRepository.save(post0);
        return post0;
    }

    @Override
    public List<Post> allPost(int courseID) {
        return postRepository.getPostByCourseId(courseID);
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

}
