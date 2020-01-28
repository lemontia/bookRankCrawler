package kr.bot.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "use_yn")
    private Boolean useYn;

    @Column(name="created_dt")
    @CreationTimestamp
    private LocalDateTime createdDateTime;


    public Book(String bookName, String author, LocalDate publishDate, String publisher) {
        this.bookName = bookName;
        this.author = author;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.useYn = true;
    }

    public void delete() {
        this.useYn = false;
    }

    public void modify(String publisher, LocalDate publishDate){
        this.publisher = publisher;
        this.publishDate = publishDate;
    }
}
