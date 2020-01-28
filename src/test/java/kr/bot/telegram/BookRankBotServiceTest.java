package kr.bot.telegram;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
class BookRankBotServiceTest {

    @Autowired
    BookRankBotService bookRankBotService;


    @Test
    @Transactional
    @Rollback(false)
    public void 크롤링_N_전송_수행테스트() throws Exception{
        // given

        // when
        bookRankBotService.doCrawling();

        // then
    }


    @Test
    @Transactional
    public void 크롤링Only() throws Exception{
        // given

        // when
        List<String> strings = bookRankBotService.bookCrawling();

        // then
        for (String string : strings) {
            System.out.println("string = " + string);
        }
    }
}