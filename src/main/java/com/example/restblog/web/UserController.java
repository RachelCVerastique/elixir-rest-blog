package com.example.restblog.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.restblog.data.User;
import com.example.restblog.data.UserRepository;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")



public class UserController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    private List<User> getAll() {
        ArrayList<User> posts = new ArrayList<>();

        return posts;
    }

    @GetMapping("{userId}")
    public Optional<User> getUserById(@PathVariable Long userId){
        return userRepository.findById(userId);

    }

    @GetMapping("/getByEmail")
    public User getByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

//    @GetMapping("/getByUserName")
//    public User getByUserName(@RequestParam String username){
//        return userRepository.findByUsername(username);
//    }

    @PostMapping
    private void createUser(@RequestBody User newUser){
        User user = newUser;
        user.setCreatedAt(LocalDate.now());
        user.setRole(User.Role.USER);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

    }


    @PutMapping("{userId}")
    private void updateUser(@PathVariable Long userId, @RequestBody User newUser){
        System.out.println("Ready to update post" + userId + newUser );
    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestBody String newPassword) {
        User user = userRepository.getById(id);
        user.setPassword(newPassword);
        System.out.println("changing password to " + user.getPassword());
    }

    @DeleteMapping("{userId}")
    private void deleteUser(@PathVariable Long userId){
        System.out.println("Deleted post" + userId);
    }


    @GetMapping("me")
    private User getByUsername(OAuth2Authentication auth) {
        String email = auth.getName();
        return userRepository.findByEmail(email);
    }


    }

