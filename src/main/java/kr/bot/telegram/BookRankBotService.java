package kr.bot.telegram;

import kr.bot.config.BookRankBot;
import kr.bot.jpa.entity.ChatUser;
import kr.bot.jpa.repo.ChatUserRepository;
import kr.bot.services.CrawlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookRankBotService {

    private final CrawlerService crawlerService;

    private final ChatUserRepository chatUserRepository;

    private final BookRankBot cgBookRankBot;


    public void doCrawling() {
        // 등록된 사람이 있는지 점검
        List<ChatUser> sendAll = chatUserRepository.findAllByUsed();

        if(sendAll.size() <= 0) {
            log.warn("[체그책순위봇] 전송할 채팅이 없습니다");
            return;
        }

        List<String> messages = bookCrawling();
        for (ChatUser chatUser : sendAll) {
            sendMessage(messages, chatUser.getChatId());
        }
    }

    /**
     * 크롤링 시작
     *      대상 책 결과를 List<String>으로 전달
     */
    @Transactional
    public List<String> bookCrawling() {
        try {
            return crawlerService.start();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(String.valueOf(e.getCause()));
            log.error(e.getMessage());
            return new LinkedList<>();
        }
    }

    // sending
    public void sendMessage(List<String> messages, Long chatId) {

        for (String message : messages) {
            try {
                cgBookRankBot.sendMessage(message, chatId);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                log.error(String.valueOf(e.getCause()));
                log.error(e.getMessage());
            }
        }
    }
}
