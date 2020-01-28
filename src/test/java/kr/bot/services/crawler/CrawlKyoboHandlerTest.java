package kr.bot.services.crawler;

import kr.bot.comm.EnumStore;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class CrawlKyoboHandlerTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CrawlKyoboHandler crawlKyoboHandler;


    @Test
    @Transactional
    public void 교보크롤링() throws Exception{
        // given
        Book book = bookRepository.findByBookName("베스트 셀프");
        if(book == null) {
            book = new Book("베스트 셀프", "마이크 베이어", LocalDate.parse("2019-09-18"), "안드로메디안");
            bookRepository.persist(book);
        }
        SetCrawler setCrawler = new SetCrawler(book
                , EnumStore.교보문고
                , "http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791135444968&orderClick=LAG&Kc="
                , true);

        // when
        String s = crawlKyoboHandler.doAction(setCrawler);

        // then
        System.out.println("s = " + s);
    }
}