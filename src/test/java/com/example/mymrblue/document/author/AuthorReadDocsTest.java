package com.example.mymrblue.document.author;

import com.example.mymrblue.author.application.AuthorDto;
import com.example.mymrblue.author.application.AuthorQueryService;
import com.example.mymrblue.author.presentation.AuthorListDto;
import com.example.mymrblue.author.presentation.AuthorReadController;
import com.example.mymrblue.document.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthorReadDocsTest extends RestDocsSupport {

    private final AuthorQueryService authorQueryService = mock(AuthorQueryService.class);

    @Override
    protected Object initController() {
        return new AuthorReadController(authorQueryService);
    }

    @DisplayName("작가 조회")
    @Test
    void read() throws Exception {
        long id = 1L;
        given(authorQueryService.getAuthor(id))
                .willReturn(new AuthorDto(id, "작가 명", "작가 필명"));

        mockMvc.perform(get("/authors/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document(
                                "read-author",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("id").description("작가 id")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(NUMBER).description("작가 id"),
                                        fieldWithPath("name").type(STRING).description("작가 명"),
                                        fieldWithPath("penName").type(STRING).description("작가 필명")
                                )
                        )

                );
    }

    @DisplayName("작가 목록 조회")
    @Test
    void readAll() throws Exception {
        AuthorListDto authorListDto = new AuthorListDto(List.of(
                new AuthorDto(1L, "작가 명", "작가 필명"),
                new AuthorDto(2L, "작가 명", "작가 필명")
        ));
        given(authorQueryService.findAll())
                .willReturn(authorListDto);

        mockMvc.perform(get("/authors")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document(
                                "read-authors",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("authors[].id").type(NUMBER).description("작가 id"),
                                        fieldWithPath("authors[].name").type(STRING).description("작가 명"),
                                        fieldWithPath("authors[].penName").type(STRING).description("작가 필명")
                                )
                        )

                );
    }
}
