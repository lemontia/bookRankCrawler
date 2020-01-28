package kr.bot.services.crawler;

import kr.bot.jpa.entity.LogCrawlerKyobo;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.LogCrawlerKyoboRepository;
import kr.bot.services.crawler.abs.CrawlHandler;
import kr.bot.services.crawler.dto.CrawlResultKyoboDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CrawlKyoboHandler extends CrawlHandler {

    private CrawlerKyobo crawler = new CrawlerKyobo();

    private String header = "[교보] ";

    private final LogCrawlerKyoboRepository logCrawlerKyoboRepository;


    public String doAction(SetCrawler setCrawler) throws IOException {
        CrawlResultKyoboDto logCrawlResultDto = crawler.doCrawler(setCrawler.getBook(), setCrawler.getUrl());

        // 결과 텍스트 만들기
        String resultText = "";

        // 결과 텍스트
        resultText = makeResult(logCrawlResultDto);
        if(resultText.equals("") == false) {
            resultText = header + resultText;
        }

        return resultText;
    }

    // 마지막 정보 조회
    // 정보가 상이한지 확인
    // 상이하면 결과 리턴
    //   아니면 "" 값 리턴(변경된 순위가 없다고 리턴하게 됨)
    @Transactional
    protected String makeResult(CrawlResultKyoboDto crawlResultKyoboDto) {

        LogCrawlerKyobo find = logCrawlerKyoboRepository.findOneBeforeByBook(crawlResultKyoboDto.getBook());
        CrawlResultKyoboDto findLog = null;
        if(find != null) {
            findLog = new CrawlResultKyoboDto(find.getBook()
                    , find.getCrawlTime()
                    , find.getTotalRank()
                    , find.getCategoryName()
                    , find.getCategoryRank());
        }

        crawlResultKyoboDto.checkFindLog(findLog);
        if(crawlResultKyoboDto.logSaveYn() == true) {
            saveLog(crawlResultKyoboDto);
        }

        return crawlResultKyoboDto.sendMessage();
    }


    // 로그저장
    @Transactional
    protected void saveLog(CrawlResultKyoboDto logCrawlResultDto) {
        LogCrawlerKyobo logCrawlerKyobo = new LogCrawlerKyobo(logCrawlResultDto);
        logCrawlerKyoboRepository.persist(logCrawlerKyobo);
    }
}