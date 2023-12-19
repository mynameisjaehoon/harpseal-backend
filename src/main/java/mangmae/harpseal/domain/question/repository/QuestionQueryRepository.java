package mangmae.harpseal.domain.question.repository;

import mangmae.harpseal.domain.choice.ChoiceEditRepositoryDto;
import mangmae.harpseal.domain.question.dto.QuestionEditRepositoryDto;
import mangmae.harpseal.domain.question.dto.QuestionSimpleRepositoryDto;
import mangmae.harpseal.global.entity.Question;

import java.util.List;

public interface QuestionQueryRepository {

    /**
     * @param quizId 찾고자하는 Question 엔티티가 속해있는 Quiz id
     * @param number Question 엔티티의 번호
     * @return 발견된 Question 엔티티
     */
    public Question findQuestion(Long quizId, int number);

    public List<QuestionSimpleRepositoryDto> findQuizQuestions(Long quizId);

    public Long updateQuestion(QuestionEditRepositoryDto dto);

    public Long deleteChoices(Long questionId);

    public void insertChoice(Question existQuestion, ChoiceEditRepositoryDto dto);

}
