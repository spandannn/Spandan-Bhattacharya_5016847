public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockData("Stock data updated:150.00");

        stockMarket.deregisterObserver(mobileApp);

        stockMarket.setStockData("Stock data updated:155.00");
    }
}