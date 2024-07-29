public class WebApp implements Observer {
    private String stockData;

    @Override
    public void update(String stockData) {
        this.stockData = stockData;
        display();
    }

    public void display() {
        System.out.println("Web App: " + stockData);
    }
}