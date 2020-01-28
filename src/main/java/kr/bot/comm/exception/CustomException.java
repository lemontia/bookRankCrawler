package kr.bot.comm.exception;

public class CustomException extends Exception {

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getCode() {
        return this.errorCode.getCode();
    }
}
