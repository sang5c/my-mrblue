package com.example.mymrblue.author.presentation;

import com.example.mymrblue.author.application.AuthorCommandService;
import com.example.mymrblue.author.application.AuthorCreateCommand;
import com.example.mymrblue.author.application.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthorCreateController {

    private final AuthorCommandService authorCommandService;

    @PostMapping("/authors")
    public AuthorDto create(@RequestBody AuthorCreateCommand command) {
        return authorCommandService.save(command);
    }
}
