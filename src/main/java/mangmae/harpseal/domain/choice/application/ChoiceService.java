package mangmae.harpseal.domain.choice.application;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;
import mangmae.harpseal.domain.choice.dto.ChoiceServiceDto;
import mangmae.harpseal.domain.choice.repository.ChoiceRepository;
import mangmae.harpseal.domain.question.dto.QuestionCreateServiceDto;
import mangmae.harpseal.global.entity.MultipleQuestionChoice;
import mangmae.harpseal.global.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    public MultipleQuestionChoice save(MultipleQuestionChoice choice) {
        return choiceRepository.save(choice);
    }

    public void storeChoices(
        final Question question,
        final QuestionCreateServiceDto dto
    ) {
        List<ChoiceServiceDto> choices = dto.getChoices();
        choices.stream()
            .map(ChoiceServiceDto::getContent)
            .map(MultipleQuestionChoice::new)
            .forEach((choice) -> {
                save(choice);
                question.addChoice(choice);
            });
    }

    public List<ChoiceServiceDto> findQuestionChoices(Long questionId) {
        List<ChoiceRepositoryDto> choices = choiceRepository.findQuestionChoices(questionId);

        return choices.stream()
            .map(ChoiceServiceDto::fromRepositoryDto)
            .toList();
    }

}
