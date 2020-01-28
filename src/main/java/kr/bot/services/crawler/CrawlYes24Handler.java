package kr.bot.services.crawler;

import kr.bot.jpa.entity.LogCrawlerYes24;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.LogCrawlerYes24Repository;
import kr.bot.services.crawler.abs.CrawlHandler;
import kr.bot.services.crawler.dto.CrawlResultYes24Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CrawlYes24Handler extends CrawlHandler {
    private CrawlerYes24 crawler = new CrawlerYes24();

    private String header = "[YES24] ";

    private final LogCrawlerYes24Repository logCrawlerYes24Repository;


    @Override
    public String doAction(SetCrawler setCrawler) throws IOException {
        CrawlResultYes24Dto crawlResultYes24Dto = crawler.doCrawler(setCrawler.getBook(), setCrawler.getUrl());

        // 결과 텍스트 만들기
        String resultText = "";

        // 결과 텍스트
        resultText = makeResult(crawlResultYes24Dto);
        if(resultText.equals("") == false) {
            resultText = header + resultText;
        }

        System.out.println("resultText = " + resultText);
        return resultText;
    }

    protected String makeResult(CrawlResultYes24Dto crawlResultYes24Dto) {
        LogCrawlerYes24 find = logCrawlerYes24Repository.findOneBeforeByBook(crawlResultYes24Dto.getBook());
        CrawlResultYes24Dto findLog = null;
        if(find != null) {
            findLog = new CrawlResultYes24Dto(find.getBook()
                    , find.getCrawlTime()
                    , find.getSalePoint()
                    , find.getCategoryName()
                    , find.getCategoryRank()
                    , find.getStayWeekName()
                    , find.getStayWeekRank());
        }
        crawlResultYes24Dto.checkFindLog(findLog);
        if(crawlResultYes24Dto.logSaveYn() == true) {
            saveLog(crawlResultYes24Dto);
        }

        return crawlResultYes24Dto.sendMessage();
    }


    @Transactional
    protected void saveLog(CrawlResultYes24Dto crawlResultYes24Dto) {
        LogCrawlerYes24 logCrawlerYes24 = new LogCrawlerYes24(crawlResultYes24Dto);
        logCrawlerYes24Repository.persist(logCrawlerYes24);
    }
}
