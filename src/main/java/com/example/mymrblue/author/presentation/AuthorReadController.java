package com.example.mymrblue.author.presentation;

import com.example.mymrblue.author.application.AuthorDto;
import com.example.mymrblue.author.application.AuthorQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthorReadController {

    private final AuthorQueryService authorQueryService;

    @GetMapping("/authors")
    public AuthorListDto list() {
        return authorQueryService.findAll();
    }

    @GetMapping("/authors/{id}")
    public AuthorDto get(@PathVariable Long id) {
        return authorQueryService.getAuthor(id);
    }
}
