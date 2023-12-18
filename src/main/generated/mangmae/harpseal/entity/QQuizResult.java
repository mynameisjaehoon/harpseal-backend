package mangmae.harpseal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import mangmae.harpseal.global.entity.QuizResult;


/**
 * QQuizResult is a Querydsl query type for QuizResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuizResult extends EntityPathBase<QuizResult> {

    private static final long serialVersionUID = -1390699987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuizResult quizResult = new QQuizResult("quizResult");

    public final mangmae.harpseal.entity.auditing.QCreatedDateEntity _super = new mangmae.harpseal.entity.auditing.QCreatedDateEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuiz quiz;

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public QQuizResult(String variable) {
        this(QuizResult.class, forVariable(variable), INITS);
    }

    public QQuizResult(Path<? extends QuizResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuizResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuizResult(PathMetadata metadata, PathInits inits) {
        this(QuizResult.class, metadata, inits);
    }

    public QQuizResult(Class<? extends QuizResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quiz = inits.isInitialized("quiz") ? new QQuiz(forProperty("quiz"), inits.get("quiz")) : null;
    }

}

