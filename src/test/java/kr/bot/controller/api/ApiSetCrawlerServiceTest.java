package kr.bot.controller.api;

import kr.bot.comm.EnumStore;
import kr.bot.jpa.entity.Book;
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
class ApiSetCrawlerServiceTest {


    @Autowired
    ApiSetCrawlerService apiSetCrawlerService;

    @Autowired
    BookRepository bookRepository;

    // 새등록
    @Test
    @Transactional
    public void 설정등록() throws Exception{
        // given
        // 책 생성
        Book book = new Book("스프링부트", "제작자", LocalDate.now(), "미래의창");
        bookRepository.persist(book);


        // when
        apiSetCrawlerService.insertSetCrawler(book.getId()
                , EnumStore.알라딘
                , "testUrl"
                , true);

        // then

    }
}