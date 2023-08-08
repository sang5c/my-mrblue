package com.example.mymrblue.author.application;

import com.example.mymrblue.author.domain.Author;
import com.example.mymrblue.author.infrastructure.AuthorCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthorCommandService {

    private final AuthorCommandRepository authorCommandRepository;

    @Transactional
    public AuthorDto save(AuthorCreateCommand command) {
        authorCommandRepository.findByPenName(command.penName())
                .ifPresent(author -> {
                    throw new IllegalArgumentException("penName is already exist");
                });
        Author author = authorCommandRepository.save(command.toEntity());
        return AuthorDto.from(author);
    }
}
