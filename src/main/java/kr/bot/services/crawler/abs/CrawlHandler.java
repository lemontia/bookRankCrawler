package kr.bot.services.crawler.abs;

import kr.bot.jpa.entity.SetCrawler;

import java.io.IOException;

public abstract class CrawlHandler<T> {

    public abstract String doAction(SetCrawler setCrawler) throws IOException;

//    protected abstract String makeResult(T crawlResultDto);
}
