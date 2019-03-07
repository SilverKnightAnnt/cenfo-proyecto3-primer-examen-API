package com.firstexam.postnav.service;

import com.firstexam.postnav.domain.Tag;
import com.firstexam.postnav.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void save(Tag tag){
        tagRepository.save(tag);
    }
}
