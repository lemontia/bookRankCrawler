package kr.bot.jpa.repo;

import kr.bot.comm.EnumStore;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.SetCrawler;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SetCrawlerRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private SetCrawlerRepository setCrawlerRepository;


    @Test
    @Transactional
    void insert() {
        //given
        Book book = new Book("스프링부트", "제작자", LocalDate.now(), "미래의창");
        bookRepository.persist(book);

        SetCrawler setCrawler = new SetCrawler(book
                , EnumStore.교보문고
                , "http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791129429506&orderClick=LAG&Kc="
                , true
        );

        //when
        setCrawlerRepository.persist(setCrawler);

        //then
        System.out.println(setCrawler);
    }


    @Test
    @Transactional
    void useYList() {
        //given

        //when
        List<SetCrawler> all = setCrawlerRepository.findAllByUseYn();

        //then
        for (SetCrawler setCrawler : all) {
            System.out.println("setCrawler = " + setCrawler);
        }
    }
}