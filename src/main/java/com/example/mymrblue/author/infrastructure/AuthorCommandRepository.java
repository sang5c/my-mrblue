package com.example.mymrblue.author.infrastructure;

import com.example.mymrblue.author.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AuthorCommandRepository {

    private final AuthorJpaRepository authorJpaRepository;


    public Optional<Author> findByPenName(String penName) {
        return authorJpaRepository.findByPenName(penName);
    }


    public Author save(Author author) {
        return authorJpaRepository.save(author);
    }
}
