package com.example.mymrblue.author.application;

import com.example.mymrblue.author.domain.Author;

public record AuthorDto(
        Long id,
        String name,
        String penName
) {
    public static AuthorDto from(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getPenName()
        );
    }
}
