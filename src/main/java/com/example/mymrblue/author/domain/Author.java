package com.example.mymrblue.author.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String penName;

    public Author(String name, String penName) {
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(penName, "penName must not be empty");
        this.name = name;
        this.penName = penName;
    }
}
