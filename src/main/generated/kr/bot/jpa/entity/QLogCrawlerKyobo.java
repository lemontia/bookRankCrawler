package kr.bot.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogCrawlerKyobo is a Querydsl query type for LogCrawlerKyobo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogCrawlerKyobo extends EntityPathBase<LogCrawlerKyobo> {

    private static final long serialVersionUID = 1213746640L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogCrawlerKyobo logCrawlerKyobo = new QLogCrawlerKyobo("logCrawlerKyobo");

    public final QBook book;

    public final StringPath categoryName = createString("categoryName");

    public final NumberPath<Long> categoryRank = createNumber("categoryRank", Long.class);

    public final DateTimePath<java.time.LocalDateTime> crawlTime = createDateTime("crawlTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> totalRank = createNumber("totalRank", Long.class);

    public QLogCrawlerKyobo(String variable) {
        this(LogCrawlerKyobo.class, forVariable(variable), INITS);
    }

    public QLogCrawlerKyobo(Path<? extends LogCrawlerKyobo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogCrawlerKyobo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogCrawlerKyobo(PathMetadata metadata, PathInits inits) {
        this(LogCrawlerKyobo.class, metadata, inits);
    }

    public QLogCrawlerKyobo(Class<? extends LogCrawlerKyobo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

