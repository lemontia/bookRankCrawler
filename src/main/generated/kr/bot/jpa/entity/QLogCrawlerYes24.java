package kr.bot.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogCrawlerYes24 is a Querydsl query type for LogCrawlerYes24
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogCrawlerYes24 extends EntityPathBase<LogCrawlerYes24> {

    private static final long serialVersionUID = 1226082411L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogCrawlerYes24 logCrawlerYes24 = new QLogCrawlerYes24("logCrawlerYes24");

    public final QBook book;

    public final StringPath categoryName = createString("categoryName");

    public final NumberPath<Long> categoryRank = createNumber("categoryRank", Long.class);

    public final DateTimePath<java.time.LocalDateTime> crawlTime = createDateTime("crawlTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> salePoint = createNumber("salePoint", Long.class);

    public final StringPath stayWeekName = createString("stayWeekName");

    public final NumberPath<Long> stayWeekRank = createNumber("stayWeekRank", Long.class);

    public QLogCrawlerYes24(String variable) {
        this(LogCrawlerYes24.class, forVariable(variable), INITS);
    }

    public QLogCrawlerYes24(Path<? extends LogCrawlerYes24> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogCrawlerYes24(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogCrawlerYes24(PathMetadata metadata, PathInits inits) {
        this(LogCrawlerYes24.class, metadata, inits);
    }

    public QLogCrawlerYes24(Class<? extends LogCrawlerYes24> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

