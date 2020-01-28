package kr.bot.services.crawler.abs;

import kr.bot.services.crawler.abs.message.MessageBookNew;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class MessageNewBookTest {

    @Test
    public void 신규로그적재() throws Exception{
        // given
        String bookName = "테스트책";
        Long totalRank = 2l;
        String categoryName = "경제";
        Long categoryRank = 1l;

        // when
        String result = new MessageBookNew(bookName, LocalDateTime.now(), totalRank, categoryName, categoryRank).result();

        // then
        System.out.println(result);
    }
}