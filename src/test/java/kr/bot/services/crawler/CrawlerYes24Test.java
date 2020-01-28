package kr.bot.services.crawler;

import kr.bot.jpa.entity.Book;
import kr.bot.services.crawler.dto.CrawlResultYes24Dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
class CrawlerYes24Test {

    CrawlerYes24 crawler;

    Book book;

    @BeforeEach
    void init() {
        book = new Book("테스트책", "저자명", LocalDate.now(), "출판사명");
        crawler = new CrawlerYes24();
    }

    @Test
    public void 예스24크롤링조회_순위있는거() throws Exception{
        // given
        String url = "http://www.yes24.com/Product/Goods/78875935?Acode=101";

        // when
        CrawlResultYes24Dto crawlResultYes24Dto = crawler.doCrawler(book, url);

        // then
        Assertions.assertTrue(crawlResultYes24Dto.getSalePoint() > 0l);
        Assertions.assertTrue((crawlResultYes24Dto.getCategoryName().equals("") == false)
                ? crawlResultYes24Dto.getCategoryRank() > 0l
                : crawlResultYes24Dto.getCategoryRank() == 0l);
    }

    @Test
    public void 예스24크롤링조회_순위없는거() throws Exception{
        // given
        String url = "http://www.yes24.com/Product/Goods/86138143";

        // when
        CrawlResultYes24Dto crawlResultYes24Dto = crawler.doCrawler(book, url);

        // then
        Assertions.assertTrue(crawlResultYes24Dto.getSalePoint() > 0l);
        Assertions.assertTrue((crawlResultYes24Dto.getCategoryName().equals("") == false)
                        ? crawlResultYes24Dto.getCategoryRank() > 0l
                        : crawlResultYes24Dto.getCategoryRank() == 0l);
    }

    @Test
    public void 순위만가져오기() throws Exception{
        // given
        String url = "/Product/addModules/BestSellerRank_Book/78875935/?categoryNumber=001001026008 &FreePrice=N";

        // when
        crawler.getRank(url);

        // then
    }

    @Test
    public void 순위만가져오기_전체없는거() throws Exception{
        // given
        String url = "/Product/addModules/BestSellerRank/86138143/?categoryNumber=017001052002";

        // when
        crawler.getRank(url);

        // then
    }

    @Test
    public void 순위만가져오기_띄어쓰기여러() throws Exception{
        // given
        String url = "/Product/addModules/BestSellerRank_Book/85462953/?categoryNumber=001001025009005 &FreePrice=N";

        // when
        crawler.getRank(url);

        // then
    }
}