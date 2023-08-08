package com.example.mymrblue.author.application;

import com.example.mymrblue.author.domain.Author;
import com.example.mymrblue.author.infrastructure.AuthorQueryRepository;
import com.example.mymrblue.author.presentation.AuthorListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorQueryService {

    private final AuthorQueryRepository authorQueryRepository;

    public AuthorListDto findAll() {
        List<Author> authors = authorQueryRepository.findAll();

        return AuthorListDto.from(authors);
    }

    public AuthorDto getAuthor(Long id) {
        Author author = authorQueryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 작가입니다. id=" + id));

        return AuthorDto.from(author);
    }
}
