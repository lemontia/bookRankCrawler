package kr.bot.schedule;

import kr.bot.telegram.BookRankBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotBookRankScheduler {

    private final BookRankBotService bookrankBotService;

    @Scheduled(cron = "0 0,30 * * * ?")
    public void startBookRank() {

        System.out.println("스케쥴 시작");


        log.info("=== schedule bookRank start");
        bookrankBotService.doCrawling();
        log.info("### schedule bookRank end");
    }
}
