package com.firstexam.postnav.web;

import com.firstexam.postnav.domain.Comment;
import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/")
    public ResponseEntity createComment(@RequestBody Comment comment) {
        comment.setTimeStamp(formatDate());
        commentService.save(comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{user}/{comment}")
    public ResponseEntity updateComment(@RequestBody Comment comment, @PathVariable("user") long userId,
                                        @PathVariable("comment") long commentId) {

        List<Comment> comments = commentService.findAll();

        for (Comment commentFound : comments) {
            if (commentFound.getUser().getId() == userId && commentFound.getId() == commentId) {
                comment.setId(commentId);
                comment.setUser(commentFound.getUser());
                comment.setPost(commentFound.getPost());
                comment.setTimeStamp(formatDate());
                commentService.save(comment);

                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/show-user-comments/{userId}")
    public List<Comment> getUserComments(@PathVariable long userId) {
        User user = new User();
        user.setId(userId);
        return commentService.findCommentByUser(user);
    }

    @GetMapping("/show-post-comments/{postId}")
    public List<Comment> getAllComments(@PathVariable long postId) {
        Post post = new Post();
        post.setId(postId);
        return commentService.findCommentByPost(post);
    }

    public String formatDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formatDate  = dateFormat.format(new Date());
        return  formatDate;
    }
}
