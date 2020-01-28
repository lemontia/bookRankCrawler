package kr.bot.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSetCrawler is a Querydsl query type for SetCrawler
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSetCrawler extends EntityPathBase<SetCrawler> {

    private static final long serialVersionUID = -676782400L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSetCrawler setCrawler = new QSetCrawler("setCrawler");

    public final QBook book;

    public final DateTimePath<java.time.LocalDateTime> createdDateTime = createDateTime("createdDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<kr.bot.comm.EnumStore> storeName = createEnum("storeName", kr.bot.comm.EnumStore.class);

    public final StringPath url = createString("url");

    public final BooleanPath useYn = createBoolean("useYn");

    public QSetCrawler(String variable) {
        this(SetCrawler.class, forVariable(variable), INITS);
    }

    public QSetCrawler(Path<? extends SetCrawler> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSetCrawler(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSetCrawler(PathMetadata metadata, PathInits inits) {
        this(SetCrawler.class, metadata, inits);
    }

    public QSetCrawler(Class<? extends SetCrawler> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

