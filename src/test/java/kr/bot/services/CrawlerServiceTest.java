package kr.bot.services;

import kr.bot.jpa.repo.LogCrawlerKyoboRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CrawlerServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    LogCrawlerKyoboRepository logCrawlerKyoboRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void 크롤링실행() throws Exception{
        // given
//        long count = logCrawlerKyoboRepository.count();

        // when
        crawlerService.start();
        em.flush();

        // then
//        long count2 = logCrawlerKyoboRepository.count();

//        System.out.println("count = " + count);
//        System.out.println("count2 = " + count2);

//        Assertions.assertTrue(count < count2);
    }

    @Test
    @Transactional
    public void 크롤링실행롤백() throws Exception{
        // given
//        long count = logCrawlerKyoboRepository.count();

        // when
        List<String> start = crawlerService.start();
//        em.flush();

        // then
        for (String s : start) {
            System.out.println("s = " + s);
        }
//        long count2 = logCrawlerKyoboRepository.count();

//        System.out.println("count = " + count);
//        System.out.println("count2 = " + count2);

//        Assertions.assertTrue(count < count2);
    }
}