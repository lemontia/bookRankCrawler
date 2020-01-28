package kr.bot.services.crawler;

import kr.bot.comm.StringUtil;
import kr.bot.jpa.entity.Book;
import kr.bot.services.crawler.abs.AbstractCrawler;
import kr.bot.services.crawler.dto.CrawlResultYes24Dto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;

public class CrawlerYes24 extends AbstractCrawler {
    String domain = "http://www.yes24.com";
    String getPositionStrStart = "/Product/addModules/BestSellerRank";
    String getPositionStrEnd = "\", function (data)";

    Long salePoint = 0l;
    String categoryName = "";
    Long categoryRank = 0l;
    String stayWeekName = "";
    Long stayWeekRank = 0l;


    @Override
    public CrawlResultYes24Dto doCrawler(Book book, String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        return rank(book, doc);
    }

    @Override
    protected CrawlResultYes24Dto rank(Book book, Document doc) throws IOException {
        // 판매지수
        Elements selects = doc.select(".gd_sellNum");
        for (Element select : selects) {
            salePoint = StringUtil.searchNumberByString(select.text());
        }

        // 순위
        String rankAjaxUrl = getRankUrl(doc);

        System.out.println("rankAjaxUrl = " + rankAjaxUrl);
        if(rankAjaxUrl.equals("") == false) {
            // 랭킹 데이터 크롤링
            getRank(rankAjaxUrl);
        }

        CrawlResultYes24Dto crawlResultYes24Dto = new CrawlResultYes24Dto(book
                , LocalDateTime.now()
                , salePoint
                , categoryName
                , categoryRank
                , stayWeekName
                , stayWeekRank);

        return crawlResultYes24Dto;
    }

    // (ajax) 랭크조회
    String getRankUrl(Document doc) {
        Elements selectRank = doc.select(".gd_infoTop");
        for (Element element : selectRank) {
            String html = element.html();
            if(html.indexOf(getPositionStrStart) >= 0){
                int idxStart = html.indexOf(getPositionStrStart);
                String endStr = getPositionStrEnd;
                int idxEnd = html.indexOf(endStr);

                String substring = html.substring(idxStart, idxEnd);
                return substring;
            }
        }
        return "";
    }

    void getRank(String url) throws IOException {
        Document doc = Jsoup.connect(domain + url).get();
        Elements selects = doc.select("dd");

        if(selects.size() > 0) {

            String text = selects.get(0).text();
            System.out.println("text = " + text);

            String[] ranks = text.split("\\|");

            // 분야별
            setCategory(ranks[0]);
            System.out.println("categoryName = " + categoryName);
            System.out.println("categoryRank = " + categoryRank);

            // 분야 topX x주
            if(ranks.length == 2) {
                setStayWeek(ranks[1].trim());
            }
        }
    }

    void setCategory(String category) {
        categoryRank = StringUtil.searchNumberByString(category.trim());
        categoryName = category.substring(0, category.indexOf(String.valueOf(categoryRank))).trim();
    }

    void setStayWeek(String stay) {
        String[] staySplit = stay.split(" ");
        String stayWeekStr = staySplit[staySplit.length - 1];

        stayWeekRank = StringUtil.searchNumberByString(stayWeekStr);
        stayWeekName = stay.substring(0
                , stay.indexOf(stayWeekStr)).trim();

        System.out.println("stayWeek = " + stayWeekRank);
        System.out.println("stayWeekCategoryName = " + stayWeekName);
    }
}
