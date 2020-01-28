package kr.bot.comm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotVaildMessageDto {

    private String field;

    private String defaultMessage;

    private Object rejectValue;


}
