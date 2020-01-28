package kr.bot.controller.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SetCrawlerSaveDto {

    @NotBlank(message = "책ID가 없습니다")
    private Long bookId;

    @NotBlank(message = "매장정보가 없습니다")
    private List<SetCrawlerDto> storeList;
}
