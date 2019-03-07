package com.firstexam.postnav.web;

import com.firstexam.postnav.domain.Likes;
import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.service.LikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikesController {
    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/")
    public ResponseEntity createLike(@RequestBody Likes likes) {
        likesService.save(likes);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity deleteLike(@PathVariable long userId, @PathVariable long postId){
        Likes like = new Likes();
        User user = new User();
        Post post = new Post();
        user.setId(userId);
        post.setId(postId);
        like.setUser(user);
        like.setPost(post);
        List<Likes> likes = likesService.findAll();
        for(Likes asd : likes){
            if(like.getUser().getId() == asd.getUser().getId() && like.getPost().getId() == asd.getPost().getId()){
                like.setId(asd.getId());
                likesService.delete(like);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/show-liked-posts/{userId}")
    public List<Post> findByUserOrderByTimestampAsc(@PathVariable long userId){
        User user = new User();
        user.setId(userId);
        List<Likes> likesList = likesService.findByUserOrderByIdDesc(user);
        List<Post> likedPosts = new ArrayList<>();
        for(Likes likes : likesList){
            likedPosts.add(likes.getPost());
        }
        return likedPosts;
    }
}
