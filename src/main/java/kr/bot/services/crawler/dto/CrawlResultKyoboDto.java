package kr.bot.services.crawler.dto;

import kr.bot.jpa.entity.Book;
import kr.bot.services.crawler.abs.message.CreateMessage;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CrawlResultKyoboDto {
    private Book book;
    private LocalDateTime crawlDateTime;
    private Long totalRank = 0l;
    private String categoryName = "";
    private Long categoryRank = 0l;

    private CrawlResultKyoboDto findLog;

    private boolean isNew = false;
    private boolean changeTotalRank = false;
    private boolean changeCategoryRank = false;


    public CrawlResultKyoboDto(Book book, LocalDateTime crawlDateTime, Long totalRank, String categoryName, Long categoryRank) {
        this.book = book;
        this.crawlDateTime = crawlDateTime;
        this.totalRank = totalRank;
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
    }


    public void checkFindLog(CrawlResultKyoboDto dto) {
        this.findLog = dto;
        compare();
    }

    protected void compare() {
        if(findLog == null) {
            this.isNew = true;
            return;
        }

        if(this.totalRank.compareTo(findLog.getTotalRank()) != 0) {
            this.changeTotalRank = true;
        }

        if(this.categoryRank.compareTo(findLog.getCategoryRank()) != 0) {
            this.changeCategoryRank = true;
        }
    }

    public boolean logSaveYn() {
        if(isNew == true ||
            this.changeTotalRank == true ||
            this.changeCategoryRank == true) {
            return true;
        }
        return false;
    }

    public String sendMessage() {
        if(isNew == true) {
            return CreateMessage.messageNewTotal(this.book.getBookName()
                    , this.crawlDateTime
                    , this.totalRank
                    , this.categoryName
                    , this.categoryRank);
        } else if(this.changeTotalRank == true || this.changeCategoryRank == true) {
            return CreateMessage.messageChangeWithTotal(this.book.getBookName()
                    , this.changeTotalRank
                    , this.totalRank
                    , findLog.totalRank
                    , this.changeCategoryRank
                    , this.categoryName
                    , this.categoryRank
                    , findLog.categoryRank
                    , findLog.crawlDateTime
                    , this.crawlDateTime
            );
        }

        return "";
    }
}
