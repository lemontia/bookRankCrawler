package kr.bot.jpa.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.LogCrawlerYes24;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static kr.bot.jpa.entity.QLogCrawlerYes24.logCrawlerYes24;

@Repository
public class LogCrawlerYes24Repository extends QuerydslRepositorySupport {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public LogCrawlerYes24Repository(JPAQueryFactory jpaQueryFactory) {
        super(LogCrawlerYes24Repository.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void persist(LogCrawlerYes24 logCrawlerYes24) {
        em.persist(logCrawlerYes24);
    }

    public LogCrawlerYes24 findOneBeforeByBook(Book book) {
        return jpaQueryFactory
                .selectFrom(logCrawlerYes24)
                .where(logCrawlerYes24.book.eq(book))
                .orderBy(logCrawlerYes24.id.desc())
                .offset(0)
                .limit(1)
                .fetchOne();
    }

    public long count() {
        return jpaQueryFactory.selectFrom(logCrawlerYes24).fetchCount();
    }
}
