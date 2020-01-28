package kr.bot.services.crawler;

import kr.bot.comm.StringUtil;
import kr.bot.jpa.entity.Book;
import kr.bot.services.crawler.abs.AbstractCrawler;
import kr.bot.services.crawler.dto.CrawlResultKyoboDto;
import kr.bot.services.crawler.abs.RankType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 교보문고
 */
public class CrawlerKyobo extends AbstractCrawler {

    final String[] checkRanks = {"주간베스트"};
    final String checkRankType = "국내도서";

    enum KyoboRankType implements RankType {
        전체, 분야
    }


    public CrawlResultKyoboDto doCrawler(Book book, String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return rank(book, doc);
    }

    protected CrawlResultKyoboDto rank(Book book, Document doc) {

        Elements selects = doc.select(".box_detail_point .rank a");

        Long totalRank = 0l;
        String categoryName = "";
        Long categoryRank = 0l;

        for (Element select : selects) {
            String text = select.text();
            if(text.equals("") == true) {
                continue;
            }

            boolean b = checkRankText(text);

            if(b == true) {
                KyoboRankType rankType = checkRankType(text);

                if(rankType.equals(KyoboRankType.전체)) {
                    totalRank = StringUtil.searchNumberByString(text);
                } else {
                    categoryName = text.split(" ")[0];
                    categoryRank = StringUtil.searchNumberByString(text);
                }
            }
        }


        return new CrawlResultKyoboDto(book
                , LocalDateTime.now()
                , totalRank, categoryName, categoryRank);
    }




    // 순위에 들지 않으면 Rank 표기가 안됨
    protected boolean checkRankText(String text) {

        for (String s1 : checkRanks) {
            if(text.indexOf(s1) >= 0) {
                return true;
            }
        }

        return false;
    }

    // 랭킹 분리
    protected KyoboRankType checkRankType(String text) {
        if(text.indexOf(checkRankType) >= 0) {
            return KyoboRankType.전체;
        }
        return KyoboRankType.분야;
    }
}
