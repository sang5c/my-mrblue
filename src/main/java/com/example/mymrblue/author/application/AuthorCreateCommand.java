package com.example.mymrblue.author.application;

import com.example.mymrblue.author.domain.Author;

public record AuthorCreateCommand(
        String name,
        String penName
) {
    public Author toEntity() {
        return new Author(name, penName);
    }
}
