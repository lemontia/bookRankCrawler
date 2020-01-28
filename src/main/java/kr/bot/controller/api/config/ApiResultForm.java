package kr.bot.controller.api.config;

import lombok.ToString;

@ToString
public class ApiResultForm<T> {
    private Header header;
    private T value;

    public static <T> ApiResultForm<T> success() {
        ApiResultForm result = new ApiResultForm();
        Header header = new Header("E0000", "OK");
        result.header = header;
        return result;
    }

    public static <T> ApiResultForm<T> success(T data) {
        ApiResultForm result = new ApiResultForm();
        Header header = new Header("E0000", "OK");
        result.header = header;
        result.value = data;
        return result;
    }

    public static <T> ApiResultForm<T> fail(String errorCode, String message) {
        ApiResultForm result = new ApiResultForm();
        Header header = new Header(errorCode, message);
        result.header = header;
        return result;
    }

    public static <T> ApiResultForm<T> fail(String errorCode, String message, T data) {
        ApiResultForm result = new ApiResultForm();
        Header header = new Header(errorCode, message);
        result.header = header;
        result.value = data;
        return result;
    }

    @ToString
    public static class Header {
        private String code;
        private String message;

        public Header() {}

        public Header(String code, String message){
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public Header getHeader() {
        return header;
    }

    public T getValue() {
        return value;
    }
}
