package kr.bot.jpa.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.bot.comm.EnumStore;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.SetCrawler;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static kr.bot.jpa.entity.QSetCrawler.setCrawler;
import static kr.bot.jpa.entity.QBook.book;

@Repository
public class SetCrawlerRepository extends QuerydslRepositorySupport {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public SetCrawlerRepository(JPAQueryFactory jpaQueryFactory) {
        super(SetCrawlerRepository.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void persist(SetCrawler setCrawler) {
        em.persist(setCrawler);
    }


    public SetCrawler findOne(Book book, EnumStore enumStore) {
        return jpaQueryFactory.selectFrom(setCrawler)
                .where(setCrawler.book.eq(book)
                        , setCrawler.storeName.eq(enumStore)
                ).fetchOne();
    }


    public long count() {
        return jpaQueryFactory.selectFrom(setCrawler).fetchCount();
    }


    public List<SetCrawler> findAllByBook(Long BookId) {
        return jpaQueryFactory.selectFrom(setCrawler)
                .where(setCrawler.book.id.eq(BookId))
                .fetch();
    }



    public SetCrawler findOneById(Long id){
        return em.find(SetCrawler.class, id);
    }

    public SetCrawler findAllByBookAndStoreName(Long bookId, EnumStore store) {
        return jpaQueryFactory.selectFrom(setCrawler)
                .where(setCrawler.book.id.eq(bookId)
                        , setCrawler.storeName.eq(store)
                )
                .fetchOne();
    }

    public List<SetCrawler> findAllByUseYn() {
        List<SetCrawler> fetch = jpaQueryFactory
                .selectFrom(setCrawler)
                .innerJoin(setCrawler.book, book).fetchJoin()
                .where(setCrawler.useYn.eq(true))
                .orderBy(setCrawler.book.bookName.asc(), setCrawler.storeName.asc())
                .fetch();

        return fetch;
    }
}
