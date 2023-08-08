package com.example.mymrblue.author.presentation;

import com.example.mymrblue.author.application.AuthorDto;
import com.example.mymrblue.author.domain.Author;

import java.util.List;

public record AuthorListDto(
        List<AuthorDto> authors
) {
    public static AuthorListDto from(List<Author> authors) {
        return new AuthorListDto(
                authors.stream()
                        .map(AuthorDto::from)
                        .toList()
        );
    }
}
