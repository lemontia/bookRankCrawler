package kr.bot.jpa.repo;

import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.LogCrawlerKyobo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
class LogCrawlerKyoboRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LogCrawlerKyoboRepository logCrawlerKyoboRepository;

    @Test
    @Transactional
    public void 마지막_1건_조회() throws Exception{
        // given
        Book book = bookRepository.findByBookName("부자 되는 법을 가르쳐 드립니다");

        LogCrawlerKyobo logCrawlerKyobo = new LogCrawlerKyobo(book
                , LocalDateTime.now()
                , 0l
                , ""
                , 0l);

        logCrawlerKyoboRepository.persist(logCrawlerKyobo);

        LogCrawlerKyobo logCrawlerKyobo2 = new LogCrawlerKyobo(book
                , LocalDateTime.now()
                , 5l
                , "경제/경영"
                , 1l);
        logCrawlerKyoboRepository.persist(logCrawlerKyobo2);

        em.flush();


        // when
        LogCrawlerKyobo oneLast = logCrawlerKyoboRepository.findOneLast();

        // then
        System.out.println("oneLast = " + oneLast);
    }


    @Test
    @Transactional
    public void 마지막적재로그_로드() throws Exception{
        // given
        Long bookId = 7l;
        Book book = bookRepository.findById(bookId);

        LogCrawlerKyobo log1 = new LogCrawlerKyobo(book
                , LocalDateTime.now().minusDays(1)
                , 3l
                , "경제"
                , 5l);

        LogCrawlerKyobo log2 = new LogCrawlerKyobo(book
                , LocalDateTime.now()
                , 1l
                , "경제"
                , 1l);

        logCrawlerKyoboRepository.persist(log1);
        logCrawlerKyoboRepository.persist(log2);

        // when
        LogCrawlerKyobo findLog = logCrawlerKyoboRepository.findOneBeforeByBook(book);

        // then
        System.out.println("===================");
        System.out.println(findLog);

        Assertions.assertTrue(log1.getId() == findLog.getId());
        Assertions.assertTrue(log2.getId() != findLog.getId());
        Assertions.assertFalse(log2.getId() == findLog.getId());
    }
}