package kr.bot.services;

import kr.bot.comm.EnumStore;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.SetCrawlerRepository;
import kr.bot.services.crawler.CrawlYes24Handler;
import kr.bot.services.crawler.abs.CrawlHandler;
import kr.bot.services.crawler.CrawlKyoboHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CrawlerService {

    public final SetCrawlerRepository setCrawlerRepository;

    private final CrawlKyoboHandler crawlerKyoboHandler;

    private final CrawlYes24Handler crawlYes24Handler;

    // 크롤링 수행 오브젝트 담기
    HashMap<EnumStore, CrawlHandler> crawlers = new HashMap();

    @PostConstruct
    void init() {
        // 교보
        crawlers.put(EnumStore.교보문고, crawlerKyoboHandler);
        crawlers.put(EnumStore.예스24, crawlYes24Handler);
    }

    @Transactional
    public List<String> start() throws IOException {
        List<SetCrawler> all = setCrawlerRepository.findAllByUseYn();

        if (all.size() == 0) {
            log.info("등록된 설정이 없습니다.");
        }

        List<String> result = new ArrayList<>();
        // Order: 책이름, 매장 순
        for (SetCrawler setCrawler : all) {
            if (crawlers.containsKey(setCrawler.getStoreName()) == true) {
                String crawlerText = crawlers.get(setCrawler.getStoreName()).doAction(setCrawler);

                if (crawlerText.equals("") == false) {
                    result.add(crawlerText);
                }
            }
        }

        return result;
    }
}
