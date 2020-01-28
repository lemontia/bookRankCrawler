package kr.bot.comm.exception;

public enum ErrorCode {
    SUCCESS("E0000")
    , ALREADY_BOOK("E0001")
    , NOT_VAILD_FROM_METHOD("E0002")
    , NO_EXIST_BOOK("E0003")
    ;

    String code;

    ErrorCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }
}
