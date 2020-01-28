package kr.bot.controller.api.dto;

import kr.bot.jpa.entity.SetCrawler;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SetCrawlerDto {
    private Long id;
    private String storeName;
    private String url;
    private boolean useYn;

    public SetCrawlerDto(SetCrawler setCrawler) {
        this.id = setCrawler.getId();
        this.storeName = setCrawler.getStoreName().name();
        this.url = setCrawler.getUrl();
        this.useYn = setCrawler.isUseYn();
    }
}
