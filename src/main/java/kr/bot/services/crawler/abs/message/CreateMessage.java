package kr.bot.services.crawler.abs.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateMessage {

    // 신규수집 - 종합
    final static String formatNewTotal = "%s (%s)\n" +
            "(신규수집) 종합순위: %s위\n" +
            "(신규수집) 분야순위(%s): %s위";

    // 신규수집 - 주간
    final static String formatNewByWeek = "%s (%s)\n" +
            "(신규수집) 분야순위(%s): %s위\n" +
            "(신규수집) %s: %s주\n" +
            "(신규수집) 판매지수: %s";

    // 전체랭킹
    static final String formatTotalRankNoChange = "(변경없음) 종합순위 : %s위";
    static final String formatTotalRankChange = "(변경) 종합순위: %s위   (%s -> %s)";
    // 분야별랭킹
    static final String formatCategoryRankNoChange = "(변경없음) 분야순위(%s): %s위";
    static final String formatCategoryRankChange = "(변경) 분야순위(%s): %s위   (%s -> %s)";
    // 랭킹 머문 주
    static final String formatStayWeekRankNoChange = "(변경없음) %s: %s주";
    static final String formatStayWeekRankChange = "(변경) %s: %s주   (%s -> %s)";
    // 판매지수
    static final String formatSalePointNoChange = "(변경없음) 판매지수: %s";
    static final String formatSalePointChange = "(변경) 판매지수: %s   (%s -> %s)";


    /**
     * 신규: 교보
     */
    public static String messageNewTotal(String bookName, LocalDateTime crawlDateTime
            , Long totalRank, String categoryName, Long categoryRank) {
        return String.format(formatNewTotal
                , bookName
                , crawlDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                , totalRank
                , categoryName
                , categoryRank);
    }

    /**
     * 변경: 교보
     */
    public static String messageChangeWithTotal(String bookName
            , boolean changeTotalRank, Long nowTotalRank, Long beforeTotalRank
            , boolean changeCategoryRank, String nowCategoryName, Long nowCategoryRank, Long beforeCategoryRank
            , LocalDateTime beforeCrawlDateTime, LocalDateTime nowCrawlDateTime) {
        return bookName +
                "\n -----------------------------" +
                "\n" + messageTotalRank(changeTotalRank, nowTotalRank, beforeTotalRank) +
                "\n" + messageCategoryRank(changeCategoryRank, nowCategoryName, nowCategoryRank, beforeCategoryRank) +
                "\n" + beforeCrawlDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + " => " +
                nowCrawlDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) +
                "\n -----------------------------";
    }

    protected static String messageTotalRank(boolean changeTotalRank, Long nowTotalRank, Long beforeTotalRank) {
        if(changeTotalRank == true) {
            return String.format(formatTotalRankChange, nowTotalRank, beforeTotalRank, nowTotalRank);
        }

        return String.format(formatTotalRankNoChange, nowTotalRank);
    }






    /**
     * 신규: yes24
     */
    public static String messageNewWeek(String bookName, LocalDateTime crawlDateTime
            , String categoryName, Long categoryRank
            , String stayWeekName, Long stayWeekRank
            , Long salePoint) {
        return String.format(formatNewByWeek
                , bookName
                , crawlDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                , categoryName
                , categoryRank
                , stayWeekName
                , stayWeekRank
                , salePoint);
    }

    /**
     * 변경: yes24
     */
    public static String messageChangeWithStayWeek(String bookName
            , boolean changeStayWeekRank, String nowStayWeekName, Long nowStayWeekRank, Long beforeStayWeekRank
            , boolean changeCategoryRank, String nowCategoryName, Long nowCategoryRank, Long beforeCategoryRank
            , Long nowSalePoint, Long beforeSalePoint
            , LocalDateTime beforeCrawlDateTime, LocalDateTime nowCrawlDateTime) {
        return bookName +
                "\n -----------------------------" +
                "\n" + messageStayWeekRank(changeStayWeekRank, nowStayWeekName, nowStayWeekRank, beforeStayWeekRank) +
                "\n" + messageCategoryRank(changeCategoryRank, nowCategoryName, nowCategoryRank, beforeCategoryRank) +
                "\n" + messageSalePoint(nowSalePoint, beforeSalePoint) +
                "\n" + beforeCrawlDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + " => " +
                nowCrawlDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) +
                "\n -----------------------------";
    }

    // 주간순위
    protected static String messageStayWeekRank(boolean changeStayWeekRank, String nowStayWeekName, Long nowStayWeekRank, Long beforeStayWeekRank) {
        if(changeStayWeekRank == true) {
            return String.format(formatStayWeekRankChange, nowStayWeekName, nowStayWeekRank, beforeStayWeekRank, nowStayWeekRank);
        }

        return String.format(formatStayWeekRankNoChange, nowStayWeekName, nowStayWeekRank);
    }

    // 분야별 순위
    protected static String messageCategoryRank(boolean changeCategoryRank, String nowCategoryName, Long nowCategoryRank, Long beforeCategoryRank) {
        if(changeCategoryRank == true) {
            return String.format(formatCategoryRankChange
                    , nowCategoryName
                    , nowCategoryRank
                    , beforeCategoryRank
                    , nowCategoryRank);
        }

        return String.format(String.format(formatCategoryRankNoChange
                , nowCategoryName
                , nowCategoryRank));
    }

    // 판매지수
    protected static String messageSalePoint(Long nowSalePoint, Long beforeSalePoint) {
        if(nowSalePoint.compareTo(beforeSalePoint) == 0) {
            return String.format(formatSalePointNoChange,
                    nowSalePoint);
        }

        return String.format(formatSalePointChange
                , nowSalePoint
                , beforeSalePoint
                , nowSalePoint);
    }
}
