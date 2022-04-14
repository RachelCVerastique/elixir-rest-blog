package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")



    public class PostController {

        @GetMapping
        private List<Post> getAll() {
            ArrayList<Post> posts = new ArrayList<>();
            posts.add(new Post(1L, "Post 1", " blah blah blah" ));
            posts.add(new Post(2L, "Post 2", " blah blah blah" ));
            posts.add(new Post(3L, "Post 3", " blah blah blah" ));
            return posts;
        }
        // Get /api/posts/5   <---- fetch the blog post with id 5
        @GetMapping("{postId}")
        public Post getById(@PathVariable Long postId){
            return new Post(postId, "post " + postId, "blah");

        }

        @PostMapping
        private void createPost(@ResponseBody Post newPost){
            System.out.println(newPost);
        }


    }

