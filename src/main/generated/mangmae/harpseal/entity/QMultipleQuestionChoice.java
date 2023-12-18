package mangmae.harpseal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import mangmae.harpseal.global.entity.MultipleQuestionChoice;


/**
 * QMultipleQuestionChoice is a Querydsl query type for MultipleQuestionChoice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMultipleQuestionChoice extends EntityPathBase<MultipleQuestionChoice> {

    private static final long serialVersionUID = -2060616142L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMultipleQuestionChoice multipleQuestionChoice = new QMultipleQuestionChoice("multipleQuestionChoice");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final QQuestion question;

    public QMultipleQuestionChoice(String variable) {
        this(MultipleQuestionChoice.class, forVariable(variable), INITS);
    }

    public QMultipleQuestionChoice(Path<? extends MultipleQuestionChoice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMultipleQuestionChoice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMultipleQuestionChoice(PathMetadata metadata, PathInits inits) {
        this(MultipleQuestionChoice.class, metadata, inits);
    }

    public QMultipleQuestionChoice(Class<? extends MultipleQuestionChoice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

