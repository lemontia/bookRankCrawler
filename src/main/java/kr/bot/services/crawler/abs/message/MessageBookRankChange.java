package kr.bot.services.crawler.abs.message;

import kr.bot.services.crawler.dto.CrawlResultKyoboDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 이전 로그 있는 경우 텍스트 가공
 */
public class MessageBookRankChange {

    String bookName;
    String categoryName;
    Long logDtoTotalRank;
    Long logDtoCategoryRank;
    LocalDateTime logCrawlDateTime;
    Long findLogTotalRank;
    Long findLogCategoryRank;
    LocalDateTime findLogCrawlDateTime;

    public MessageBookRankChange(CrawlResultKyoboDto log, CrawlResultKyoboDto findLog) {
        this.bookName = log.getBook().getBookName();
        this.categoryName = log.getCategoryName();
        this.logDtoTotalRank = log.getTotalRank();
        this.logDtoCategoryRank = log.getCategoryRank();
        this.logCrawlDateTime = log.getCrawlDateTime();
        this.findLogTotalRank = findLog.getTotalRank();
        this.findLogCategoryRank = findLog.getCategoryRank();
        this.findLogCrawlDateTime = findLog.getCrawlDateTime();
    }

    public String result() {
        return bookName +
                "\n -----------------------------" +
                "\n" + totalRank() + categoryRank() +
                "\n" + findLogCrawlDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + " => " +
                logCrawlDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) +
                "\n -----------------------------";
    }

    protected String totalRank() {
        long changeRank = (findLogTotalRank - logDtoTotalRank);

        if(changeRank == 0) {
            return "(변경없음) 종합순위 : " + logDtoTotalRank + "위\n";
        } else {
            return "(변경) 종합순위: " + logDtoTotalRank + "위"
                    + "   (" + findLogTotalRank + " -> " + logDtoTotalRank + ") \n";
        }
    }

    protected String categoryRank() {
        long changeRank = (findLogCategoryRank - logDtoCategoryRank);
        if(changeRank == 0) {
            return "(변경없음) 분야순위(" + categoryName + "): " + findLogCategoryRank + "위\n";
        } else {
            return "(변경) 분야순위(" + categoryName + "): " + logDtoCategoryRank  + "위"
                    + "   (" + findLogCategoryRank + " -> " + logDtoCategoryRank + ")";
        }
    }
}
