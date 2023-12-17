package mangmae.harpseal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mangmae.harpseal.domain.choice.dto.ChoiceRepositoryDto;
import mangmae.harpseal.domain.quiz.service.dto.question.QuestionCreateServiceDto;
import mangmae.harpseal.domain.quiz.repository.dto.question.QuestionRepositoryDto;
import mangmae.harpseal.entity.type.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String content;
    private int number;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne(mappedBy = "question")
    private Attachment attachment;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MultipleQuestionChoice> choices = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    public Question(String content, int number, String answer, QuestionType questionType) {
        this.content = content;
        this.number = number;
        this.answer = answer;
        this.questionType = questionType;
    }

    public Question(QuestionCreateServiceDto dto) {
        this(dto.getContent(), dto.getNumber(), dto.getAnswer(), QuestionType.by(dto.getType()));
    }

    /**
     * 연관관계에 있는 quiz 엔티티를 바꾸는 메서드.<br>
     * Quiz 엔티티에서 Question 목록을 추가할 때 사용되는 메서드이다.
     * @param quiz 연관관계이 있는 퀴즈
     */
    public void changeQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void addChoice(MultipleQuestionChoice choice) {
        choices.add(choice);
        choice.changeQuestion(this);
    }

    public void addAttachment(Attachment attachment) {
        this.attachment = attachment;
        attachment.changeQuestion(this);
    }

    public QuestionRepositoryDto toRepositoryDto(String attachmentPath, List<ChoiceRepositoryDto> choices) {
        return QuestionRepositoryDto.builder()
                .id(id)
                .content(content)
                .number(number)
                .answer(answer)
                .type(questionType)
                .attachmentPath(attachmentPath)
                .choices(choices)
                .build();
    }

}
