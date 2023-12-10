package mangmae.harpseal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuizThumbnail is a Querydsl query type for QuizThumbnail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuizThumbnail extends EntityPathBase<QuizThumbnail> {

    private static final long serialVersionUID = 2061403356L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuizThumbnail quizThumbnail = new QQuizThumbnail("quizThumbnail");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuiz quiz;

    public QQuizThumbnail(String variable) {
        this(QuizThumbnail.class, forVariable(variable), INITS);
    }

    public QQuizThumbnail(Path<? extends QuizThumbnail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuizThumbnail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuizThumbnail(PathMetadata metadata, PathInits inits) {
        this(QuizThumbnail.class, metadata, inits);
    }

    public QQuizThumbnail(Class<? extends QuizThumbnail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quiz = inits.isInitialized("quiz") ? new QQuiz(forProperty("quiz"), inits.get("quiz")) : null;
    }

}

