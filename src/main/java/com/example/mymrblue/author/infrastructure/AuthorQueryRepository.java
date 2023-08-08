package com.example.mymrblue.author.infrastructure;

import com.example.mymrblue.author.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AuthorQueryRepository {

    private final AuthorJpaRepository authorJpaRepository;

    public List<Author> findAll() {
        return authorJpaRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorJpaRepository.findById(id);
    }
}
