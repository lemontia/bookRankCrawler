package kr.bot;

public class TestClass {

    public static void main(String[] args) {

        String str = "국내도서 주간베스트 9위";

        String result = str.replaceAll("[^0-9]","");

        System.out.println(result);

    }
}
