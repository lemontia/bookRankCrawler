package kr.bot.services;

import kr.bot.jpa.entity.Book;
import kr.bot.jpa.repo.LogCrawlerKyoboRepository;
import kr.bot.services.crawler.CrawlerKyobo;
import kr.bot.services.crawler.dto.CrawlResultKyoboDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


@RunWith(MockitoJUnitRunner.class)
class CrawlerKyoboTest {

    CrawlerKyobo crawler;

    Book book;

    @Autowired
    LogCrawlerKyoboRepository logCrawlerKyoboRepository;

    @BeforeEach
    void init() {
        book = new Book("테스트책", "저자명", LocalDate.now(), "출판사명");
        crawler = new CrawlerKyobo();
    }

    @Test
    public void 교보크롤링조회_순위있는거() throws Exception{
        // given
        String url = "http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9791135455063";


        // when
        CrawlResultKyoboDto logCrawlerKyobo = crawler.doCrawler(book, url);

        // then
        System.out.println(logCrawlerKyobo);
        Assertions.assertTrue((logCrawlerKyobo.getTotalRank() > 0l));
        Assertions.assertTrue((logCrawlerKyobo.getCategoryName().equals("") == false));
    }

    @Test
    public void 교보크롤링조회_순위없는거() throws Exception{
        //given
        String url = "http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791129429506&orderClick=LEa&Kc=";

        //when
        CrawlResultKyoboDto logCrawlerKyobo = crawler.doCrawler(book, url);

        //then
        Assertions.assertTrue((logCrawlerKyobo.getTotalRank() == 0l));
        Assertions.assertTrue((logCrawlerKyobo.getCategoryName().equals("")));
    }
}