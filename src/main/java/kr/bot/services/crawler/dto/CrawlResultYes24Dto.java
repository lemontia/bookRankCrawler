package kr.bot.services.crawler.dto;


import kr.bot.jpa.entity.Book;
import kr.bot.services.crawler.abs.CrawlResultDto;
import kr.bot.services.crawler.abs.message.CreateMessage;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CrawlResultYes24Dto implements CrawlResultDto {
    private Book book;
    private LocalDateTime crawlDateTime;
    private Long salePoint = 0l;
    private String categoryName = "";
    private Long categoryRank = 0l;
    private String stayWeekName = "";
    private Long stayWeekRank = 0l;

    private CrawlResultYes24Dto findLog;

    private boolean isNew = false;
    private boolean changeStayWeekRank = false;
    private boolean changeCategoryRank = false;


    public CrawlResultYes24Dto(Book book, LocalDateTime crawlDateTime, Long salePoint, String categoryName, Long categoryRank, String stayWeekName, Long stayWeekRank) {
        this.book = book;
        this.crawlDateTime = crawlDateTime;
        this.salePoint = salePoint;
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
        this.stayWeekName = stayWeekName;
        this.stayWeekRank = stayWeekRank;
    }

    public void checkFindLog(CrawlResultYes24Dto dto) {
        this.findLog = dto;
        compare();
    }

    protected void compare() {
        if(findLog == null) {
            this.isNew = true;
            return;
        }

        if(this.stayWeekRank.compareTo(findLog.getStayWeekRank()) != 0) {
            this.changeStayWeekRank = true;
        }

        if(this.categoryRank.compareTo(findLog.getCategoryRank()) != 0) {
            this.changeCategoryRank = true;
        }
    }

    public boolean logSaveYn() {
        if(isNew == true ||
                this.changeCategoryRank == true ||
                this.changeStayWeekRank == true) {
            return true;
        }
        return false;
    }

    public String sendMessage() {
        if(isNew == true) {
            return CreateMessage.messageNewWeek(this.book.getBookName()
                    , this.crawlDateTime
                    , this.categoryName
                    , this.categoryRank
                    , this.stayWeekName
                    , this.stayWeekRank
                    , this.salePoint);
        } else if(this.changeCategoryRank == true || this.changeStayWeekRank == true) {
            return CreateMessage.messageChangeWithStayWeek(this.book.getBookName()
                    , this.changeStayWeekRank
                    , this.stayWeekName
                    , this.stayWeekRank
                    , findLog.stayWeekRank
                    , this.changeCategoryRank
                    , this.categoryName
                    , this.categoryRank
                    , this.findLog.categoryRank
                    , this.salePoint
                    , this.findLog.salePoint
                    , findLog.crawlDateTime
                    , this.crawlDateTime
            );
        }

        return "";
    }
}
