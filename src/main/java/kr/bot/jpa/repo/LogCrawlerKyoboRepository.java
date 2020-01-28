package kr.bot.jpa.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.LogCrawlerKyobo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static kr.bot.jpa.entity.QLogCrawlerKyobo.logCrawlerKyobo;

@Repository
public class LogCrawlerKyoboRepository extends QuerydslRepositorySupport {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public LogCrawlerKyoboRepository(JPAQueryFactory jpaQueryFactory) {
        super(LogCrawlerKyoboRepository.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public void persist(LogCrawlerKyobo logCrawlerKyobo) {
        em.persist(logCrawlerKyobo);
    }


    public LogCrawlerKyobo findOneLast() {
        return jpaQueryFactory.selectFrom(logCrawlerKyobo)
                .orderBy(logCrawlerKyobo.id.desc())
                .limit(1)
                .fetchOne();
    }

    public LogCrawlerKyobo findOneBeforeByBook(Book book) {
        return jpaQueryFactory
                .selectFrom(logCrawlerKyobo)
                .where(logCrawlerKyobo.book.eq(book))
                .orderBy(logCrawlerKyobo.id.desc())
                .offset(0)
                .limit(1)
                .fetchOne();
    }




    public long count() {
        return jpaQueryFactory.selectFrom(logCrawlerKyobo).fetchCount();
    }

    /**
     * 날짜와 책을 검색조건에 넣어 하루이상 지나지 않았다면 이전것 보여주기
     */
    public LogCrawlerKyobo findOneNotOver(Book book) {
        return jpaQueryFactory.selectFrom(logCrawlerKyobo)
                .where(logCrawlerKyobo.book.eq(book)
                        , logCrawlerKyobo.crawlTime.goe(LocalDateTime.now().minusHours(1))
                )
                .orderBy(logCrawlerKyobo.id.desc())
                .limit(1)
                .fetchOne();
    }
}
