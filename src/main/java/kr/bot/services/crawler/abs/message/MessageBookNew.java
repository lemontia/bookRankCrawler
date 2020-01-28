package kr.bot.services.crawler.abs.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageBookNew {

    final String message = "%s (%s)\n" +
            "(신규수집) 종합순위: %s\n" +
            "(신규수집) 분야순위(%s): %s";

    String bookName;
    LocalDateTime crawlDateTime;
    Long totalRank;
    String categoryName;
    Long categoryRank;

    public MessageBookNew(String bookName, LocalDateTime crawlDateTime, Long totalRank, String categoryName, Long categoryRank) {
        this.bookName = bookName;
        this.crawlDateTime = crawlDateTime;
        this.totalRank = totalRank;
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
    }

    public String result() {
        return String.format(message
                , bookName
                , crawlDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                , totalRank
                , categoryName
                , categoryRank);
    }
}
