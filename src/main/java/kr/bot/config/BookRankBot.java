package kr.bot.config;

import kr.bot.services.ChatUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
public class BookRankBot extends TelegramLongPollingBot {

    ChatUserService chatUserService;

    String chatName = "체그책순위봇";

    String token = "";

    public BookRankBot(String token
            , ChatUserService chatUserService){
        this.token = token;
        this.chatUserService = chatUserService;
    }

    @Override
    @Transactional
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        Long chatId = message.getChatId();

        String returnMessage = "";
        if(text.equals("등록하기")) {
            User from = message.getFrom();
            String firstName = from.getFirstName();
            String lastName = from.getLastName();
            log.info(" =========== chatId: {}", chatId);

            boolean isNew = chatUserService.regist(chatName
                    , chatId
                    , firstName
                    , lastName
                    , false);

            if(isNew == true) {
                returnMessage = "등록되었습니다. 관리자 승인까지 기다려주세요";
            } else {
                returnMessage = "이미 등록된 계정입니다";
            }

        } else {
            returnMessage = "알 수 없는 메세지 입니다";
        }

        try {
            sendMessage(returnMessage, chatId);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 메세지전달
     */
    public void sendMessage(String message, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().enableHtml(true);
        sendMessage.setChatId(chatId);

        sendMessage.setText(message);
        execute(sendMessage);
    }

    /**
     * 메세지 전달
     * @param list
     * @param chatId
     */
    void sendMessage(List<String> list, Long chatId){

        try {
            SendMessage sendMessage = new SendMessage().enableHtml(true);
            sendMessage.setChatId(chatId);

            if(list == null) {
                sendMessage.setText("검색된 데이터가 없습니다. 이상이 없는지 확인해주세요");
                execute(sendMessage);
                return;
            }

//            if(list.size() == 0) {
//                sendMessage.setText("변경된 순위가 없습니다.");
//                execute(sendMessage);
//                return;
//            }

            // 목록별로 모두 전달
            for (String s : list) {
                sendMessage(s, chatId);
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "cg_bookrank_bot";
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
