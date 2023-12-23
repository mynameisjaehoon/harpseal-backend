package mangmae.harpseal.domain.comment.api;

import mangmae.harpseal.domain.comment.application.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(CommentController.class)
@Transactional
class CommentControllerTest {

    @Autowired
    CommentService commentService;

    MockMvc mockMvc;

    @BeforeEach
    void before() {

        mockMvc = standaloneSetup(new CommentController(commentService)).build();
    }

    @Test
    void createCommentTest() throws Exception {
        mockMvc.perform(post("/api/v1/quiz/1/comment/new"))
            .andExpect(status().isSeeOther());
    }


}