package kr.bot.controller.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BookForm {

    private String bookName;

    private String author;

    private String publisher;

    private LocalDate publishDate;
}
