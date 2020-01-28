package kr.bot.jpa;

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
class BookRepositoryTest {

    @Autowired
    private BookRepository booksRepository;


    @Test
    @Transactional
    void insert() {
        Book books = new Book("스프링부트", "제작자", LocalDate.now(), "미래의창");
        booksRepository.persist(books);

        System.out.println("========================");
        System.out.println(books);
        System.out.println("========================");
    }
}