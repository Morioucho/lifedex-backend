package com.morioucho.lifedex.controller;

import com.morioucho.lifedex.model.Post;
import com.morioucho.lifedex.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> foundPosts = postService.getPosts();

        if(foundPosts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(foundPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        Post foundPost = postService.getPostByID(id);

        if(foundPost == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foundPost, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        if(post.getTitle().isEmpty() || post.getContent().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        Post foundPost = postService.getPostByID(id);

        if(foundPost == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
