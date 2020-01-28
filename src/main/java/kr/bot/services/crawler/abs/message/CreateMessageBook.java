package kr.bot.services.crawler.abs.message;

import kr.bot.services.crawler.dto.CrawlResultKyoboDto;

@Deprecated
public class CreateMessageBook {

    String header;
    private CrawlResultKyoboDto now;
    private CrawlResultKyoboDto findLog;

    public CreateMessageBook (String header, CrawlResultKyoboDto now, CrawlResultKyoboDto findLog) {
        this.header = header;
        this.now = now;
        this.findLog = findLog;
    }

    public String result() {
        if(findLog == null) {
            return header + new MessageBookNew(now.getBook().getBookName()
                    , now.getCrawlDateTime()
                    , now.getTotalRank()
                    , now.getCategoryName()
                    , now.getCategoryRank())
                    .result();
        }

        if(findLog.getTotalRank().compareTo(now.getTotalRank()) == 0
                || findLog.getCategoryRank().compareTo(now.getCategoryRank()) == 0) {
            return "";
        }

        return header + new MessageBookRankChange(now, findLog)
                .result();
    }
}
