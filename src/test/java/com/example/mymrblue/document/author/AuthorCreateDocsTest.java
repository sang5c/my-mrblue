package com.example.mymrblue.document.author;

import com.example.mymrblue.author.application.AuthorCommandService;
import com.example.mymrblue.author.application.AuthorCreateCommand;
import com.example.mymrblue.author.application.AuthorDto;
import com.example.mymrblue.author.presentation.AuthorCreateController;
import com.example.mymrblue.document.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorCreateDocsTest extends RestDocsSupport {

    private final AuthorCommandService authorCommandService = mock(AuthorCommandService.class);

    @Override
    protected Object initController() {
        return new AuthorCreateController(authorCommandService);
    }

    @DisplayName("작가 등록")
    @Test
    void create() throws Exception {
        AuthorCreateCommand command = new AuthorCreateCommand("작가 명", "작가 필명");
        AuthorDto authorDto = new AuthorDto(1L, "작가 명", "작가 필명");
        given(authorCommandService.save(command))
                .willReturn(authorDto);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document(
                                "create-author",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("name").type(STRING).description("작가 명"),
                                        fieldWithPath("penName").type(STRING).description("작가 필명")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(NUMBER).description("작가 id"),
                                        fieldWithPath("name").type(STRING).description("작가 명"),
                                        fieldWithPath("penName").type(STRING).description("작가 필명")
                                )
                        )

                );
    }
}
