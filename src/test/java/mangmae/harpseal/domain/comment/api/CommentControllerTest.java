package mangmae.harpseal.domain.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.comment.application.CommentService;
import mangmae.harpseal.domain.comment.dto.CreateCommentRequestForm;
import mangmae.harpseal.domain.comment.dto.CreateCommentResponseDto;
import mangmae.harpseal.domain.comment.dto.DeleteCommentRequestForm;
import mangmae.harpseal.domain.comment.repository.CommentRepository;
import mangmae.harpseal.global.entity.Comment;
import mangmae.harpseal.global.entity.Quiz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
class CommentControllerTest {

    @Autowired
    EntityManager em;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MockMvc mvc;

    private String testPassword;
    private Long quizId;
    private Long commentId;
    private ObjectMapper objectMapper;

    @BeforeEach
    void before() {
        objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());
        testPassword = UUID.randomUUID().toString().substring(0, 8);

        Quiz quiz = new Quiz("comment_test_quiz1", "this is comment test quiz", testPassword);
        Comment testComment = new Comment("this is test comment", testPassword);
        quiz.addComment(testComment);
        em.persist(quiz);
        em.persist(testComment);

        quizId = quiz.getId();
        commentId = testComment.getId();
    }

    @Test
    @DisplayName("댓글 생성 성공")
    void createCommentSuccess() throws Exception {
        CreateCommentRequestForm form = new CreateCommentRequestForm("comment test example", testPassword);
        String formJson = objectMapper.writeValueAsString(form);

        MvcResult mvcResult = mvc.perform(post("/api/v1/quiz/{quizId}/comment", quizId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson)
            )
            .andExpect(status().isOk()) // expect HTTP 200 OK
            .andExpect(jsonPath("$.content").isNotEmpty())
            .andExpect(jsonPath("$.createdBy").isNotEmpty())
            .andExpect(jsonPath("$.like").isNumber())
            .andExpect(jsonPath("$.like").isNotEmpty())
            .andExpect(jsonPath("$.createdDate").isNotEmpty())
            .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        log.info("result={}", contentAsString);

        CreateCommentResponseDto result = objectMapper.readValue(contentAsString, CreateCommentResponseDto.class);
        Long commentId = result.getCommentId();
        Optional<Comment> findComment = commentRepository.findById(commentId);
        assertThat(findComment.isPresent()).isTrue();
    }

    /**
     * 댓글 작성 실패 테스트
     * 존재하지 않는 퀴즈 번호로 요청한 경우 댓글 생성에 실패한다.
     */
    @Test
    @DisplayName("댓글 생성 실패")
    void createCommentFail() throws Exception {

        CreateCommentRequestForm idFailForm = new CreateCommentRequestForm("quiz id fail", testPassword);
        String idFailJson = objectMapper.writeValueAsString(idFailForm);

        mvc.perform(
                post("/api/v1/quiz/{quizId}/comment", Long.MAX_VALUE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(idFailJson)
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("댓글 삭제 성공")
    void deleteCommentSuccess() throws Exception {

        DeleteCommentRequestForm form = new DeleteCommentRequestForm(commentId, testPassword);
        String formJson = objectMapper.writeValueAsString(form);

        mvc.perform(
                delete("/api/v1/quiz/{quizId}/comment", quizId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(formJson)
            )
            .andExpect(status().isOk());

        Optional<Comment> findComment = commentRepository.findById(commentId);
        assertThat(findComment.isEmpty()).isTrue();
    }



}