package kr.bot.comm;

public class StringUtil {

    public static Long searchNumberByString(String str) {
        String result = str.replaceAll("[^0-9]","");

        return Long.valueOf(result);
    }
}
