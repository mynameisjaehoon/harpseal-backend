package mangmae.harpseal.domain.quiz.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("퀴즈 목록 조회 응답테스트")
    public void getQuizListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quiz"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("단일 퀴즈 조회 응답테스트")
    public void getSingleQuizTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quiz/1"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("퀴즈 수정 응답테스트")
    public void editQuizResponseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/quiz/1/edit"))
            .andExpect(status().isOk());
    }
}