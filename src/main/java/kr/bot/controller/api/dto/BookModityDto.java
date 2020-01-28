package kr.bot.controller.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookModityDto {
    @Min(1)
    private Long id;
    @NotBlank(message = "책이름을 입력해주세요")
    private String bookName;
    @NotBlank(message = "저자를 입력해주세요")
    private String author;

    private String publisher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
