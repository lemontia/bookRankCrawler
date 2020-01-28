package kr.bot.services;

import kr.bot.jpa.entity.ChatUser;
import kr.bot.jpa.repo.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatUserService {

    @Autowired
    ChatUserRepository chatUserRepository;

    @Transactional
    public boolean regist(String chatName, Long chatId, String firstName, String lastName, boolean useYn) {

        ChatUser findUser = chatUserRepository.findOne(chatName, chatId);
        if(findUser == null) {
            ChatUser chatUser = new ChatUser(chatName, chatId, firstName, lastName, false);
            chatUserRepository.persist(chatUser);

            return true;
        }

        return false;
    }
}
