package kr.bot.config;

import kr.bot.services.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Configuration
public class TelegramBotManager {

    @Autowired
    ChatUserService chatUserService;

    @Value("${telegram.token}")
    String telegramBotToken;

    @PostConstruct
    void init() {
        ApiContextInitializer.init();
    }

    /**
     * 체그 챗봇 등록
     * @return
     */
    @Bean
    public BookRankBot bookRankBot() {
        if(telegramBotToken.equals("[token]")==true) {
            throw new RuntimeException("텔레그램 챗봇 API Token 을 입력해 주세요(application-*.properties)");
        }

        BookRankBot cgBookRankBot = new BookRankBot(telegramBotToken
                , chatUserService);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(cgBookRankBot);

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        return cgBookRankBot;
    }
}
