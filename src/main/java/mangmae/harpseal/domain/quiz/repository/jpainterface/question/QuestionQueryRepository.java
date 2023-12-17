package mangmae.harpseal.domain.quiz.repository.jpainterface.question;

import mangmae.harpseal.domain.quiz.repository.dto.question.ChoiceEditRepositoryDto;
import mangmae.harpseal.domain.quiz.repository.dto.question.QuestionEditRepositoryDto;
import mangmae.harpseal.entity.Question;

import java.util.List;

public interface QuestionQueryRepository {

    /**
     * @param quizId 찾고자하는 Question 엔티티가 속해있는 Quiz id
     * @param number Question 엔티티의 번호
     * @return 발견된 Question 엔티티
     */
    public Question findQuestion(Long quizId, int number);

    public Long updateQuestion(QuestionEditRepositoryDto dto);

    public Long deleteChoices(Long questionId);

    public void insertChoice(Question existQuestion, ChoiceEditRepositoryDto dto);

}
