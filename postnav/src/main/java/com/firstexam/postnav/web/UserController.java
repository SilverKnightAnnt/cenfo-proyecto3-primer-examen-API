package com.firstexam.postnav.web;

import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.Tag;
import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.service.PostService;
import com.firstexam.postnav.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/login/{nick}")
    public User logIn(@PathVariable String nick){
        return userService.findUserByNickName(nick);
    }

    @GetMapping("/recommended-posts/{user}")
    public List<Post> recommendedPosts(@PathVariable long user){
        List<Post> showedPosts = filterPosts(user);
        return showedPosts;
    }

    @GetMapping("/general-view/{userId}")
    public List<Post> generalView(@PathVariable long userId){

        List<Post> allPosts = postService.findByOrderByTimeStampDesc();
        List<Post> showedPosts = filterPosts(userId);

        for (Post post : allPosts){
            if(showedPosts.contains(post)){
            }else{
                showedPosts.add(post);
            }
        }

        return showedPosts;
    }

    public List<Post> filterPosts(long userId){

        User user = userService.findUserById(userId);

        List<Post> allPosts = postService.findByOrderByTimeStampDesc();
        List<Post> definedPosts = new ArrayList<>();

        for(Tag tag : user.getTags()){
            for(Post post : allPosts){
                for(Tag postTag : post.getTags()){
                    if(definedPosts.contains(post)){
                    }else if(postTag.getName() == tag.getName()){
                        definedPosts.add(post);
                    }
                }
            }
        }

        return definedPosts;
    }

}
