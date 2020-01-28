package kr.bot.jpa.entity;

import kr.bot.services.crawler.dto.CrawlResultYes24Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name="log_crawler_yes24")
public class LogCrawlerYes24 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name="crawl_dt")
    private LocalDateTime crawlTime;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="category_rank")
    private Long categoryRank;

    @Column(name="stay_week_name")
    private String stayWeekName;

    @Column(name="stay_week_rank")
    private Long stayWeekRank;

    @Column(name="sale_point")
    private Long salePoint;


    public LogCrawlerYes24(Book book, LocalDateTime crawlTime, String categoryName, Long categoryRank, String stayWeekName, Long stayWeekRank, Long salePoint) {
        this.book = book;
        this.crawlTime = crawlTime;
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
        this.stayWeekName = stayWeekName;
        this.stayWeekRank = stayWeekRank;
        this.salePoint = salePoint;
    }

    public LogCrawlerYes24(CrawlResultYes24Dto crawlResultYes24Dto) {
        this.book = crawlResultYes24Dto.getBook();
        this.crawlTime = crawlResultYes24Dto.getCrawlDateTime();
        this.categoryName = crawlResultYes24Dto.getCategoryName();
        this.categoryRank = crawlResultYes24Dto.getCategoryRank();
        this.stayWeekName = crawlResultYes24Dto.getStayWeekName();
        this.stayWeekRank = crawlResultYes24Dto.getStayWeekRank();
        this.salePoint = crawlResultYes24Dto.getSalePoint();
    }
}
