package kr.bot.jpa.entity;

import kr.bot.jpa.entity.key.ChatUserKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "chatUser")
@IdClass(ChatUserKey.class)
public class ChatUser {
    @Id
    @Column(name = "chat_name")
    private String chatName;

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "use_yn")
    private Boolean useYn;

    @Column(name="created_dt")
    @CreationTimestamp
    private LocalDateTime createdDateTime;


    public ChatUser(String chatName, Long chatId, String firstName, String lastName, boolean useYn) {
        this.chatName = chatName;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.useYn = useYn;
    }
}
