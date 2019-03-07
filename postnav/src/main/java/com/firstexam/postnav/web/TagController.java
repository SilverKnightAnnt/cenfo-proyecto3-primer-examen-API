package com.firstexam.postnav.web;

import com.firstexam.postnav.domain.Tag;
import com.firstexam.postnav.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/")
    public ResponseEntity createTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Tag> getAllTags() {
        return tagService.findAll();
    }
}
