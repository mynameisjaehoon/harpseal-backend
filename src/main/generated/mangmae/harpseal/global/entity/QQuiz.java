package mangmae.harpseal.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuiz is a Querydsl query type for Quiz
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuiz extends EntityPathBase<Quiz> {

    private static final long serialVersionUID = -1293808251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuiz quiz = new QQuiz("quiz");

    public final mangmae.harpseal.global.entity.auditing.QCreatedDateEntity _super = new mangmae.harpseal.global.entity.auditing.QCreatedDateEntity(this);

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final StringPath password = createString("password");

    public final NumberPath<Integer> playTime = createNumber("playTime", Integer.class);

    public final ListPath<Question, QQuestion> questions = this.<Question, QQuestion>createList("questions", Question.class, QQuestion.class, PathInits.DIRECT2);

    public final ListPath<QuizResult, QQuizResult> quizResults = this.<QuizResult, QQuizResult>createList("quizResults", QuizResult.class, QQuizResult.class, PathInits.DIRECT2);

    public final QQuizThumbnail thumbnail;

    public final StringPath title = createString("title");

    public QQuiz(String variable) {
        this(Quiz.class, forVariable(variable), INITS);
    }

    public QQuiz(Path<? extends Quiz> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuiz(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuiz(PathMetadata metadata, PathInits inits) {
        this(Quiz.class, metadata, inits);
    }

    public QQuiz(Class<? extends Quiz> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.thumbnail = inits.isInitialized("thumbnail") ? new QQuizThumbnail(forProperty("thumbnail"), inits.get("thumbnail")) : null;
    }

}

