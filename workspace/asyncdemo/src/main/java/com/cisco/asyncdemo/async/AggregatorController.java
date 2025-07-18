package com.cisco.asyncdemo.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/posts")
public class AggregatorController {
    @Autowired
    private  AggregatorService service;
    // combination of User and Post
    record PostsDTO(String title, String name) {}
    // write a method return combination/ aggregation of Users and Posts
    @GetMapping()
    public List<PostsDTO> getPosts() {
        CompletableFuture<List<User>> users = service.getUsers(); // non-blocking
        CompletableFuture<List<Post>> posts = service.getPosts(); // non-blocking

        // barrier
        List<Post> postList = posts.join();
        List<User> userList = users.join();

        // java 8 stream api
        return postList.stream().map(post -> {
            String username = userList.stream()
                    .filter(user -> user.id() == post.userId()).findFirst().get().name();
                return new PostsDTO(post.title(), username);
        }).collect(Collectors.toList());
    }
}
