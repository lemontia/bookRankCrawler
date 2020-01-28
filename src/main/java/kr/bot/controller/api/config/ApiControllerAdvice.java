package kr.bot.controller.api.config;

import kr.bot.comm.exception.CustomException;
import kr.bot.comm.exception.ErrorCode;
import kr.bot.comm.exception.NotVaildMessageDto;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(value = {"kr.bot.controller.api"})
public class ApiControllerAdvice {


    /**
     * 커스텀 에러
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ApiResultForm customException(CustomException customException) {
        return ApiResultForm.fail(customException.getCode(), customException.getMessage());
    }

    /**
     * @Valid 에러
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResultForm methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        List<NotVaildMessageDto> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.add(new NotVaildMessageDto(fieldError.getField()
                    , fieldError.getDefaultMessage()
                    , fieldError.getRejectedValue()));
        }

        return ApiResultForm.fail(ErrorCode.NOT_VAILD_FROM_METHOD.getCode()
                , errors.get(0).getDefaultMessage()
                , errors
        );
    }
}
