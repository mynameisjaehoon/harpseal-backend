package mangmae.harpseal.domain.question.application;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.question.dto.QuestionSimpleRepositoryDto;
import mangmae.harpseal.domain.question.repository.QuestionRepository;
import mangmae.harpseal.domain.question.dto.QuestionSimpleServiceDto;
import mangmae.harpseal.global.entity.Question;
import mangmae.harpseal.global.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final FileUtil fileUtil;

    public Question save(Question question) {
        Question findQuestion = questionRepository.save(question);
        return findQuestion;
    }

    public List<QuestionSimpleServiceDto> findQuizQuestions(Long quizId) {
        List<QuestionSimpleRepositoryDto> questions = questionRepository.findQuizQuestions(quizId);
        List<QuestionSimpleServiceDto> result = new ArrayList<>();

        // 각 퀴즈 정보마다 타입과 첨부파일 데이터 정보를 서비스 DTO에 추가해 반환해야한다.
        for (QuestionSimpleRepositoryDto question : questions) {
            QuestionSimpleServiceDto serviceDto = QuestionSimpleServiceDto.fromRepositoryDto(question);
            Long questionId = question.getId();
            // 첨부파일 적용
            String path = question.getAttachmentPath();
            String attachment = fileUtil.loadImageBase64(path);
            serviceDto.addAttachmentData(attachment);

            result.add(serviceDto);
        }

        return result;
    }
}
