package com.firstexam.postnav.web;

import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity createPost(@RequestBody Post post) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formatDate  = dateFormat.format(new Date());
        post.setTimeStamp(formatDate);
        postService.save(post);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Post> getAll(){
        return postService.findAll();
    }

    @GetMapping("/show-user-posts/{userId}")
    public List<Post> findByUserOrderByTimestampAsc(@PathVariable long userId){
        User user = new User();
        user.setId(userId);
        return postService.findByUserOrderByTimestampDesc(user);
    }
}
