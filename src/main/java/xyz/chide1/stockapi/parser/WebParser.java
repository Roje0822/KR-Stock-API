package xyz.chide1.stockapi.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebParser {

    public static Document getHtmlAsString(String stockCode) throws IOException {
        String url = "https://finance.naver.com/item/main.naver?code=" + stockCode;
        Document document = Jsoup.connect(url).get();
        return document;
    }
}
