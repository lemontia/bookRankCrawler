package kr.bot.services.crawler.abs.message;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class CreateMessageTest {

    @Test
    public void 신규_교보() throws Exception{
        // given

        // when
        String s = CreateMessage.messageNewTotal("책이름", LocalDateTime.now()
                , 1l
                , "인문"
                , 2l
        );

        // then
        System.out.println(s);
    }
}