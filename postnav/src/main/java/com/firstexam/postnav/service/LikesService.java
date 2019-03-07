package com.firstexam.postnav.service;

import com.firstexam.postnav.domain.Likes;
import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.repository.LikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikesService {
    private final LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Transactional(readOnly = true)
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }

    public void save(Likes likes){
        likesRepository.save(likes);
    }

    @Transactional(readOnly = true)
    public List<Likes> findByUserOrderByIdDesc(User user){
        return likesRepository.findByUserOrderByIdDesc(user);
    }

    public void delete(Likes likes){
        likesRepository.delete(likes);
    }
}
