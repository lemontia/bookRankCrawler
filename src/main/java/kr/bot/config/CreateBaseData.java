package kr.bot.config;

import kr.bot.comm.EnumStore;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.BookRepository;
import kr.bot.jpa.repo.SetCrawlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Transactional(readOnly = true)
public class CreateBaseData implements ApplicationRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SetCrawlerRepository setCrawlerRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        Book book = saveBook();
        saveSetCrawler(book);
    }

    /**
     * 크롤링 설정 저장
     */
    @Transactional
    void saveSetCrawler(Book book) {
        EnumStore 교보문고 = EnumStore.교보문고;

        SetCrawler one = setCrawlerRepository.findOne(book, EnumStore.교보문고);

        if(one == null) {
            SetCrawler setCrawler = new SetCrawler(book
                    , 교보문고
                    , "http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791135444968&orderClick=LEa&Kc="
                    , true
            );
            setCrawlerRepository.persist(setCrawler);
        }
    }



    /**
     * 책 1개 저장
     */
    @Transactional
    Book saveBook() {
        String bookName = "베스트 셀프";

        Book findBook = bookRepository.findByBookName(bookName);
        if(findBook == null) {
            Book book = new Book(bookName
                    , "마이크 베이어"
                    , LocalDate.parse("2019-09-18", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    , "안드로메디안"
            );
            bookRepository.persist(book);
            return book;
        }

        return findBook;
    }
}
