package mangmae.harpseal.domain.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.comment.application.CommentService;
import mangmae.harpseal.domain.comment.dto.CreateCommentRequestForm;
import mangmae.harpseal.domain.comment.dto.CreateCommentResponseDto;
import mangmae.harpseal.global.entity.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
    CommentService commentService;

    @Autowired
    MockMvc mvc;

    private String testPassword;
    private Long quizId;
    private ObjectMapper objectMapper;

    @BeforeEach
    void before() {
        objectMapper = new ObjectMapper();
        testPassword = UUID.randomUUID().toString().substring(0, 8);

        Quiz quiz = new Quiz("comment_test_quiz1", "this is comment test quiz", testPassword);
        em.persist(quiz);

        quizId = quiz.getId(); // PK IDENTITY 전략을 사용하기 때문에 영속상태로 만드는순간 insert 쿼리가 날아가고, 캐시에 저장된다.
    }

    @Test
    @DisplayName("댓글 생성 성공")
    void createCommentSuccess() throws Exception {
        CreateCommentRequestForm form = new CreateCommentRequestForm("comment test example", testPassword);
        String formJson = objectMapper.writeValueAsString(form);
        String requestURL = "/api/v1/quiz/" + quizId + "/comment/new";

        log.info("request new comment URL=[{}]", requestURL);

        mvc.perform(post(requestURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson)
            )
            .andExpect(status().isOk()) // expect HTTP 200 OK
            .andExpect(jsonPath("$.content").isNotEmpty())
            .andExpect(jsonPath("$.createdBy").isNotEmpty())
            .andExpect(jsonPath("$.like").isNumber())
            .andExpect(jsonPath("$.like").isNotEmpty())
            .andExpect(jsonPath("$.createdDate").isNotEmpty());

    }

    /**
     * 댓글 작성 실패 테스트
     * 1. 퀴즈의 비밀번호가 일치하지 않는 경우
     * 2. 존재하지 않는 퀴즈 번호로 요청한 경우
     */
    @Test
    @DisplayName("댓글 생성 실패")
    void createCommentFail() throws Exception {
        String failPassword = testPassword + UUID.randomUUID().toString().substring(0, 3);
        String idFailURL = "/api/v1/quiz/" + Long.MAX_VALUE + "/comment/new";

        CreateCommentRequestForm idFailForm = new CreateCommentRequestForm("quiz id fail", testPassword);
        CreateCommentRequestForm passwordFailForm = new CreateCommentRequestForm("quiz password fail", failPassword);
        String passwordFailJson = objectMapper.writeValueAsString(passwordFailForm);

        mvc.perform(
                post("/api/v1/quiz/" + quizId + "/comment/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(passwordFailJson)
            )
            .andExpect(status().isBadRequest());

        mvc.perform(
                post(idFailURL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(idFailURL)
            )
            .andExpect(status().isBadRequest());
    }


}