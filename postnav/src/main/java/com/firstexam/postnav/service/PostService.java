package com.firstexam.postnav.service;

import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void save(Post post){
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findByUserOrderByTimestampDesc(User user){
        return postRepository.findByUserOrderByTimeStampDesc(user);
    }

    @Transactional(readOnly = true)
    public List<Post> findByOrderByTimeStampDesc(){
        return postRepository.findByOrderByTimeStampDesc();
    }
}
