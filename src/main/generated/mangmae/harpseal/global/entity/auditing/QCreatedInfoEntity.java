package mangmae.harpseal.global.entity.auditing;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreatedInfoEntity is a Querydsl query type for CreatedInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCreatedInfoEntity extends EntityPathBase<CreatedInfoEntity> {

    private static final long serialVersionUID = -1769271774L;

    public static final QCreatedInfoEntity createdInfoEntity = new QCreatedInfoEntity("createdInfoEntity");

    public final QCreatedDateEntity _super = new QCreatedDateEntity(this);

    public final StringPath createdBy = createString("createdBy");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public QCreatedInfoEntity(String variable) {
        super(CreatedInfoEntity.class, forVariable(variable));
    }

    public QCreatedInfoEntity(Path<? extends CreatedInfoEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreatedInfoEntity(PathMetadata metadata) {
        super(CreatedInfoEntity.class, metadata);
    }

}

