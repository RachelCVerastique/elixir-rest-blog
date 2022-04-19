package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
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
            posts.add(new Post(1L,new User(), "Apple Pen", " I have a pen. I have a apple. APPLE PEN!" ));
            posts.add(new Post(2L,new User(), "Pineapple Pen", " I have a pen. I have pineapple. PINEAPPLE PEN!" ));
            posts.add(new Post(3L,new User(), "Pen Pineapple Apple Pen", " Apple pen, pineapple pen... PEN PINEAPPLE APPLE PEN!" ));
            return posts;
        }
        // Get /api/posts/5   <---- fetch the blog post with id 5
        @GetMapping("{postId}")
        public Post getById(@PathVariable Long postId){
            return new Post(postId,new User(), "post " + postId, "blah");

        }

        @PostMapping
        private void createPost(@RequestBody Post newPost){
            System.out.println("Ready to add post" + newPost);
        }


        @PutMapping("{id}")
        private void updatePost(@PathVariable Long id, @RequestBody Post post){
            System.out.println("Ready to update post" + id + post );
        }

        @DeleteMapping("{postId}")
        private void deletePost(@PathVariable Long postId){
            System.out.println("Deleted post" + postId);
        }

    }

