package kr.bot.services.crawler;

import kr.bot.comm.EnumStore;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class CrawlYes24HandlerTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CrawlYes24Handler crawlYes24Handler;


    @Test
    @Transactional
    @Rollback(false)
    public void 예스24크롤링() throws Exception{
        // given
        Book book = bookRepository.findByBookName("베스트 셀프");
        if(book == null) {
            book = new Book("베스트 셀프", "마이크 베이어", LocalDate.parse("2019-09-18"), "안드로메디안");
            bookRepository.persist(book);
        }
        SetCrawler setCrawler = new SetCrawler(book
                , EnumStore.예스24
                , "http://www.yes24.com/Product/Goods/78875935?Acode=101"
                , true);

        // when
        String s = crawlYes24Handler.doAction(setCrawler);

        // then
        System.out.println("s = " + s);
    }
}