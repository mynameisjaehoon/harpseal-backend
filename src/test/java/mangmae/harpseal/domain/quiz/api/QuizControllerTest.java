package mangmae.harpseal.domain.quiz.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class QuizControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("퀴즈 목록 조회")
    public void getQuizListTest() throws Exception {

        mvc.perform(
                get("/api/v1/quiz")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content[*].id").isNotEmpty())
            .andExpect(jsonPath("$.content[*].title").hasJsonPath())
            .andExpect(jsonPath("$.content[*].description").isNotEmpty())
            .andExpect(jsonPath("$.content[*].imageData").isNotEmpty());

    }

    @Test
    @DisplayName("단일 퀴즈 조회 응답테스트")
    public void getSingleQuizTest() throws Exception {
        mvc.perform(get("/api/v1/quiz/1"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("퀴즈 수정 응답테스트")
    public void editQuizResponseTest() throws Exception {
        mvc.perform(put("/api/v1/quiz/1/edit"))
            .andExpect(status().isOk());
    }
}