package mangmae.harpseal.domain.choice.repository;

import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;

import java.util.List;

public interface ChoiceQueryRepository {

    public List<ChoiceRepositoryDto> findQuestionChoices(Long questionId);

}
