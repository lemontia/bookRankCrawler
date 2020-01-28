package kr.bot.jpa.entity;


import kr.bot.services.crawler.dto.CrawlResultKyoboDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name="log_crawler_kyobo")
public class LogCrawlerKyobo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name="crawl_dt")
    private LocalDateTime crawlTime;

    @Column(name="total_rank")
    private Long totalRank;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="category_rank")
    private Long categoryRank;


    public LogCrawlerKyobo(Book book, LocalDateTime crawlTime, Long totalRank, String categoryName, Long categoryRank) {
        this.book = book;
        this.crawlTime = crawlTime;
        this.totalRank = totalRank;
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
    }


    public LogCrawlerKyobo(CrawlResultKyoboDto logCrawlResultDto) {
        this.book = logCrawlResultDto.getBook();
        this.crawlTime = logCrawlResultDto.getCrawlDateTime();
        this.totalRank = logCrawlResultDto.getTotalRank();
        this.categoryName = logCrawlResultDto.getCategoryName();
        this.categoryRank = logCrawlResultDto.getCategoryRank();
    }

}
