package com.example.restblog.web;

import com.example.restblog.data.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.restblog.services.EmailService;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")



    public class PostController {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final CategoriesRepository categoriesRepository;
    private final EmailService emailService;

    public PostController(PostsRepository postsRepository, UserRepository userRepository, CategoriesRepository categoriesRepository, EmailService emailService) {
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
        this.emailService = emailService;
    }

    @GetMapping
        private List<Post> getAll() {
            List<Post> posts = postsRepository.findAll();
        System.out.println(posts);
            return posts;
        }
        // Get /api/posts/5   <---- fetch the blog post with id 5
        @GetMapping("{postId}")
        private Optional<Post> getById(@PathVariable Long postId){
            return postsRepository.findById(postId);
        }

        @PostMapping
        private void createPost(@RequestBody Post newPost, OAuth2Authentication auth){
            User author = userRepository.findByEmail(auth.getName());
            newPost.setAuthor(author);
            List<Category> categories = new ArrayList<>();
            categories.add(categoriesRepository.findCategoryByName("auto"));
            categories.add(categoriesRepository.findCategoryByName("instruments"));
            newPost.setCategories(categories);
            postsRepository.save(newPost);
            System.out.println("Post created");
            emailService.prepareAndSend(newPost, "news", "some body");
        }


        @PutMapping("{id}")
        private void updatePost(@PathVariable Long id, @RequestBody Post newPost){
           Post postToUpdate = postsRepository.getById(id);
           postToUpdate.setContent(newPost.getContent());
           postsRepository.save(postToUpdate);
            System.out.println("Ready to update post" + id + newPost );
        }

        @DeleteMapping("{postId}")
        private void deletePost(@PathVariable Long postId){
            postsRepository.deleteById(postId);

            System.out.println("Deleted post" + postId);
        }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }






    }

