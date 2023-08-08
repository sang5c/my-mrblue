package com.example.mymrblue.author.infrastructure;

import com.example.mymrblue.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorJpaRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByPenName(String penName);
}
