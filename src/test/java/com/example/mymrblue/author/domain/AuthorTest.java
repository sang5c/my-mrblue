package com.example.mymrblue.author.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthorTest {

    @DisplayName("이름은 비어있을 수 없다")
    @ParameterizedTest
    @NullAndEmptySource
    void emptyName(String name) {
        assertThatThrownBy(() -> new Author(name, "필명"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("필명은 비어있을 수 없다")
    @ParameterizedTest
    @NullAndEmptySource
    void emptyPenName(String penName) {
        assertThatThrownBy(() -> new Author("이름", penName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
