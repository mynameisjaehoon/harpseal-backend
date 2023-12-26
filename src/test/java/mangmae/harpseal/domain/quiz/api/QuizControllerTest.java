package mangmae.harpseal.domain.quiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.quiz.dto.QuizSearchRequestCond;
import mangmae.harpseal.global.entity.MultipleQuestionChoice;
import mangmae.harpseal.global.entity.Question;
import mangmae.harpseal.global.entity.Quiz;
import mangmae.harpseal.global.entity.type.QuestionType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
class QuizControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EntityManager em;

    private String testPassword; // 테스트용 퀴즈 비밀번호
    private Long testQuizId; // 테스트용 퀴즈 엔티티 ID
    private ObjectMapper objectMapper;

    @BeforeEach
    void before() {
        objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());
        testPassword = UUID.randomUUID().toString().substring(0, 8);

        Quiz testQuiz = new Quiz("test quiz title", "test quiz description", testPassword);
        em.persist(testQuiz);
        testQuizId = testQuiz.getId();

        Question newQuestion = new Question("example question", 1, "example answer", QuestionType.MULTIPLE);
        for (int j = 1; j <= 5; j++) {
            MultipleQuestionChoice newChoice = new MultipleQuestionChoice(j, "choice" + j);
            em.persist(newChoice);
            newQuestion.addChoice(newChoice);
        }
        em.persist(newQuestion);
        testQuiz.addQuestion(newQuestion);

    }

    @Test
    @DisplayName("단일 퀴즈 조회 응답테스트")
    public void getSingleQuizTest() throws Exception {

        mvc.perform(
                RestDocumentationRequestBuilders.get("/api/v1/quiz/{quizId}", testQuizId)
            )
            .andExpect(status().isOk())
            .andDo(
                document(
                    "단일 퀴즈 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    HeaderDocumentation.responseHeaders(

                    ),
                    pathParameters(
                        parameterWithName("quizId").description("퀴즈 ID")
                    ),
                    responseFields(
                        fieldWithPath("quizId").description("퀴즈 ID").type(NUMBER),
                        fieldWithPath("title").description("퀴즈 제목").type(STRING),
                        fieldWithPath("description").description("퀴즈 설명").type(STRING),
                        fieldWithPath("thumbnailData").optional().description("퀴즈 썸네일이미지 데이터").type(STRING),
                        fieldWithPath("likeCount").description("퀴즈 좋아요 수").type(NUMBER),
                        fieldWithPath("playTime").description("퀴즈 플레이 횟수").type(NUMBER),
                        subsectionWithPath("questions").description("문제 목록"),
                        fieldWithPath("questions[].id").description("문제 ID").type(NUMBER),
                        fieldWithPath("questions[].content").description("문제 내용").type(STRING),
                        fieldWithPath("questions[].number").description("문제 번호").type(NUMBER),
                        fieldWithPath("questions[].answer").description("문제 정답").type(STRING),
                        fieldWithPath("questions[].attachmentData").optional().description("문제 첨부파일 데이터").type(STRING),
                        fieldWithPath("questions[].attachmentType").optional().description("문제 첨부파일 타입").type(STRING),
                        fieldWithPath("questions[].choices[].number").description("선택지 번호").type(NUMBER),
                        fieldWithPath("questions[].choices[].content").description("선택지 내용").type(STRING)
                    )
                )
            );
    }

    @Test
    @DisplayName("퀴즈 목록 조회 테스트")
    public void getQuizListTest() throws Exception {

        QuizSearchRequestCond condition = new QuizSearchRequestCond("NONE");
        String formJson = objectMapper.writeValueAsString(condition);

        mvc.perform(
                get("/api/v1/quiz")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(formJson)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content[*].id").isNotEmpty())
            .andExpect(jsonPath("$.content[*].title").isNotEmpty())
            .andExpect(jsonPath("$.content[*].description").isNotEmpty())
            .andDo(print())
            .andDo(
                document(
                    "퀴즈 목록 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("page").optional().description("조회 퀴즈 페이지"),
                        parameterWithName("size").optional().description("조회 사이즈")
                    ),
                    requestFields(
                        fieldWithPath("title").optional().type(STRING).description("퀴즈 검색 제목"),
                        fieldWithPath("searchType").optional().type(STRING).description("검색 조건")
                    ),
                    responseFields(
                        fieldWithPath("content").type(ARRAY).description("조회 퀴즈 목록 데이터"),
                        fieldWithPath("content[].id").type(NUMBER).description("퀴즈 ID"),
                        fieldWithPath("content[].title").type(STRING).description("퀴즈 제목"),
                        fieldWithPath("content[].description").type(STRING).description("퀴즈 설명"),
                        fieldWithPath("content[].imageData").optional().type(STRING).description("퀴즈 썸네일 이미지 데이터"),
                        subsectionWithPath("pageable").type(OBJECT).description("페이징 정보"),
                        fieldWithPath("last").type(BOOLEAN).description("마지막 페이지 여부"),
                        fieldWithPath("totalPages").type(NUMBER).description("전체 페이지 수"),
                        fieldWithPath("totalElements").type(NUMBER).description("전체 데이터 수"),
                        fieldWithPath("first").type(BOOLEAN).description("첫번째 페이지 여부"),
                        fieldWithPath("size").type(NUMBER).description("페이지 사이즈"),
                        fieldWithPath("number").type(NUMBER).description("페이지 번(0부터 시작)"),
                        subsectionWithPath("sort").type(OBJECT).description("URL 정렬 정보"),
                        fieldWithPath("numberOfElements").type(NUMBER).description("가져온 데이터 수"),
                        fieldWithPath("empty").type(BOOLEAN).description("데이터 공백 여부")
                    )
                )
            );
    }


//    @Test
//    @DisplayName("퀴즈 목록 조회")
//    public void getQuizListTest() throws Exception {
//
//        mvc.perform(
//                get("/api/v1/quiz")
//                    .accept(MediaType.APPLICATION_JSON)
//            )
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.content").exists())
//            .andExpect(jsonPath("$.content[*].id").isNotEmpty())
//            .andExpect(jsonPath("$.content[*].title").isNotEmpty())
//            .andExpect(jsonPath("$.content[*].description").isNotEmpty())
//            .andExpect(jsonPath("$.content[*].imageData").isNotEmpty());
//
//    }



//    @Test
//    @DisplayName("퀴즈 수정 응답테스트")
//    public void editQuizResponseTest() throws Exception {
//        mvc.perform(put("/api/v1/quiz/1/edit"))
//            .andExpect(status().isOk());
//    }
}