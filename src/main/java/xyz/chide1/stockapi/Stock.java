package xyz.chide1.stockapi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Stock {

    private final Document document;

    public Stock(String stockCode) {
        String url = "https://finance.naver.com/item/main.naver?code=";
        try {
            this.document = Jsoup.connect(url + stockCode).get();
        } catch (IOException e) {
            throw new RuntimeException("주식 불러오기를 실패했습니다");
        }
    }

    public String getCurrentStockPrice() {
        Elements contentWrap = document.body().getElementsByClass("content_wrap");
        Element blindElement = contentWrap.get(0).getElementsByClass("spot").get(0).getElementsByClass("blind").get(1);
        System.out.println(blindElement.text());

        return document.select("content_wrap").val();
    }

    public static void main(String[] args) {
        Stock stock = new Stock("004100");
        System.out.println(stock.getCurrentStockPrice());
    }

}
