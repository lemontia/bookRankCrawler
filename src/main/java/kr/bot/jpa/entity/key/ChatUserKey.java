package kr.bot.jpa.entity.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatUserKey implements Serializable {
    private String chatName;
    private Long chatId;
}
