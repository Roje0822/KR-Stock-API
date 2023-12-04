package xyz.chide1.stockapi;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import xyz.chide1.stockapi.parser.WebParser;

public class Stock {

    private final Document document;

    public Stock(String stockCode) {
        this.document = WebParser.getHtmlAsString(stockCode);
    }

    /**
     * 현재 주식 가격을 반환합니다.
     * @return 주식 가격
     */
    public int getCurrentStockPrice() {
        String stockPriceElement = getBlind(3);
        return Integer.parseInt(stockPriceElement.split(" ")[1].replace(",", ""));
    }

    /**
     * 전일대비 상승 가격을 반환합니다.
     * @return 상승 가격
     */
    public int getIncreasePrice() {
        String increasePriceElement = getBlind(3);
        int increasePrice = Integer.parseInt(increasePriceElement.split(" ")[4].replace(",", ""));
        if (isIncreasePrice()) return increasePrice;
        else return increasePrice * -1;
    }

    /**
     * 전일대비 상승 퍼센트를 반환합니다.
     * @return 상승 퍼센트
     */
    public float getIncreasePercent() {
        String increasePercentElement = getBlind(3);
        float increasePercent = Float.parseFloat(increasePercentElement.split(" ")[6]);
        if (isIncreasePrice()) return increasePercent;
        else return increasePercent * -1;
    }

    /**
     * 전일가를 반환합니다.
     * @return 전일가
     */
    public int getPreviousStockPrice() {
        String previousStockPriceElement = getBlind(4);
        return Integer.parseInt(previousStockPriceElement.split(" ")[1].replace(",", ""));
    }

    /**
     * 시가를 반환합니다.
     * @return 시가
     */
    public int getOpeningPrice() {
        String stockOpeningPriceElement = getBlind(5);
        return Integer.parseInt(stockOpeningPriceElement.split(" ")[1].replace(",", ""));
    }

    /**
     * 고가를 반환합니다.
     * @return 고가
     */
    public int getHigh() {
        String  stockLowElement = getBlind(6);
        return Integer.parseInt(stockLowElement.split(" ")[1].replace(",", ""));
    }

    /**
     * 저가를 반환합니다.
     * @return 저가
     */
    public int getLow() {
        String  stockHighElement = getBlind(8);
        return Integer.parseInt(stockHighElement.split(" ")[1].replace(",", ""));
    }

    /**
     * 상한가를 반환합니다.
     * @return 상한가
     */
    public int getUpperLimitPrice() {
        String stockUpperLimitPrice = getBlind(7);
        return Integer.parseInt(stockUpperLimitPrice.split(" ")[1].replace(",", ""));
    }

    /**
     * 하한가를 반환합니다.
     * @return 하한가
     */
    public int getLowerLimitPrice() {
        String stockLowerLimitPrice = getBlind(9);
        return Integer.parseInt(stockLowerLimitPrice.split(" ")[1].replace(",", ""));
    }

    /**
     * 거래량을 반환합니다.
     * @return 거래량
     */
    public int getVolume() {
        String  stockVolume = getBlind(10);
        return Integer.parseInt(stockVolume.split(" ")[1].replace(",", ""));
    }

    /**
     * 거래대금을 반환합니다.
     * @return 거래대금
     */
    public long getTradingValue() {
        String stockTradingValue = getBlind(11);
        return Long.parseLong(stockTradingValue.split(" ")[1].replace(",", "").replace("백만", "")) * 1000000;
    }

    private boolean isIncreasePrice() {
        String blindInformation = getBlind(3);
        return blindInformation.contains("상승");
    }

    private String getBlind(int i) {
        Elements totalInformation = document.body().getElementsByClass("new_totalinfo");
        return totalInformation.get(0).getElementsByClass("blind").get(0).getElementsByTag("dd").get(i).text();
    }

}
