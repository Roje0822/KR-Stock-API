package xyz.chide1.stockapi.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebParser {

    public static Document getHtmlAsString(String stockCode) {
        String url = "https://finance.naver.com/item/main.naver?code=" + stockCode;
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("주식 불러오기를 실패했습니다");
        }
        return document;
    }
}
