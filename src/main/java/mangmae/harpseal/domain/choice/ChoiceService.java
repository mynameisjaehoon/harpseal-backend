package mangmae.harpseal.domain.choice;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.entity.MultipleQuestionChoice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    public MultipleQuestionChoice save(MultipleQuestionChoice choice) {
        return choiceRepository.save(choice);
    }

}
