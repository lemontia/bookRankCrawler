package kr.bot.jpa.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.bot.jpa.entity.Book;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static kr.bot.jpa.entity.QBook.book;

@Repository
public class BookRepository extends QuerydslRepositorySupport {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public BookRepository(JPAQueryFactory jpaQueryFactory) {
        super(Book.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void persist(Book books){
        em.persist(books);
    }


    public List<Book> findAll() {
        return jpaQueryFactory.selectFrom(book)
                .where(book.useYn.eq(true))
                .orderBy(book.publishDate.desc())
                .fetch();
    }

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    public Book findByBookName(String bookName) {
        return jpaQueryFactory.selectFrom(book)
                .where(book.bookName.eq(bookName)
                        , book.useYn.eq(true)
                )
                .fetchOne();
    }
}
