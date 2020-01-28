package kr.bot.jpa.entity;

import kr.bot.comm.EnumStore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name="set_crawler")
public class SetCrawler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "store_name")
    private EnumStore storeName;

    @Column(name="url")
    private String url;

    @Column(name="use_yn")
    private boolean useYn;

    @Column(name="created_dt")
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    public SetCrawler(Book book, EnumStore storeName, String url, boolean useYn) {
        this.book = book;
        this.storeName = storeName;
        this.url = url;
        this.useYn = useYn;
    }


    public void changeUrlAndUseYn(String url, boolean useYn){
        this.url = url;
        this.useYn = useYn;
    }
}
