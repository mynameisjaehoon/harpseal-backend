package mangmae.harpseal.domain.quiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import mangmae.harpseal.domain.quiz.dto.QuizCreateRequestForm;
import mangmae.harpseal.domain.quiz.dto.QuizDeleteRequestDto;
import mangmae.harpseal.domain.quiz.dto.QuizSearchRequestCond;
import mangmae.harpseal.global.entity.MultipleQuestionChoice;
import mangmae.harpseal.global.entity.Question;
import mangmae.harpseal.global.entity.Quiz;
import mangmae.harpseal.global.entity.type.QuestionType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.*;
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

    /**
     * 단일 퀴즈 조회 테스트
     */
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

        ConstraintDescriptions conditionSearchTypeConstraints = new ConstraintDescriptions(QuizSearchRequestCond.class);
        List<String> searchTypeConstraints = conditionSearchTypeConstraints.descriptionsForProperty("searchType");

        mvc.perform(
                get("/api/v1/quiz?page=0&size=2")
                    .contentType(APPLICATION_JSON)
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
                        fieldWithPath("searchType").type(STRING).description("검색 조건").attributes(key("constraints").value("`NONE`, `COUNT_ASC`, `COUNT_DESC`, `RECENT`, `OLD` 중 하나여야 한다."))
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
                        fieldWithPath("number").type(NUMBER).description("페이지 번호(0부터 시작)"),
                        subsectionWithPath("sort").type(OBJECT).description("URL 정렬 정보"),
                        fieldWithPath("numberOfElements").type(NUMBER).description("가져온 데이터 수"),
                        fieldWithPath("empty").type(BOOLEAN).description("데이터 공백 여부")
                    )
                )
            );
    }

    @Test
    @DisplayName("퀴즈 생성")
    public void quizCreateTest() throws Exception {
        QuizCreateRequestForm requestForm = new QuizCreateRequestForm("quiz name", "12345", "example new quiz");
        String formJson = objectMapper.writeValueAsString(requestForm);

        MockMultipartFile formFile = new MockMultipartFile("form", "", "application/json", formJson.getBytes());
        MockMultipartFile imageFile = new MockMultipartFile("thumbnail", "", "image/png", (byte[]) null);

        mvc.perform(
                multipart("/api/v1/quiz/new")
                    .file(formFile)
                    .file(imageFile)
                    .accept(APPLICATION_JSON, IMAGE_PNG, IMAGE_JPEG)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(
                document(
                    "퀴즈 생성",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestPartBody("form"),
                    requestPartFields(
                        "form",
                        fieldWithPath("title").type(STRING).description("퀴즈 이름"),
                        fieldWithPath("password").type(STRING).description("퀴즈 비밀번호"),
                        fieldWithPath("description").type(STRING).description("퀴즈 설명")
                    )
                )
            );

    }

    @Test
    @DisplayName("퀴즈 삭제")
    void deleteQuiz() throws Exception {

        QuizDeleteRequestDto deleteForm = new QuizDeleteRequestDto(testPassword);
        String deleteFormJson = objectMapper.writeValueAsString(deleteForm);

        mvc.perform(
                RestDocumentationRequestBuilders.delete("/api/v1/quiz/{quizId}", testQuizId)
                    .contentType(APPLICATION_JSON)
                    .content(deleteFormJson)
            )
            .andExpect(status().isSeeOther())
            .andExpect(header().string("Location", "http://localhost:8080/api/v1/quiz"))
            .andDo(
                document(
                    "퀴즈 삭제",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("quizId").description("삭제 퀴즈 ID")
                    ),
                    requestFields(
                        fieldWithPath("password").type(STRING).description("삭제 퀴즈 패스워드")
                    )
                )
            );
    }

    @Test
    @DisplayName("퀴즈 좋아요 증가")
    void addQuizLike() throws Exception {
        mvc.perform(
                RestDocumentationRequestBuilders.post("/api/v1/quiz/{quizId}/like", testQuizId)
            )
            .andExpect(status().isOk())
            .andDo(
                document(
                    "퀴즈 좋아요 증가",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("quizId").description("대상 퀴즈 ID")
                    )
                )
            );
    }

    @Test
    @DisplayName("퀴즈 수정")
    void editQuiz() throws Exception {

    }



}