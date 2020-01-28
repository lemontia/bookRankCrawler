package kr.bot.telegram;

import kr.bot.config.BookRankBot;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BotSenderTest {

    @Autowired
    BookRankBot cgBookRankBot;

    @Test
    public void 텔레그램_전송() throws Exception{
        // given
        String message = "덕하!";
        Long chatId = 639820272L;

        // when
        cgBookRankBot.sendMessage(message, chatId);


        // then

    }

}