public class AdapterPatternTest {
    public static void main(String[] args) {
        PaymentProcessor gPayProcessor = new GPayAdapter(new GPay());
        gPayProcessor.processPayment(100.0);

        PaymentProcessor paytmProcessor = new PaytmAdapter(new Paytm());
        paytmProcessor.processPayment(200.0);

        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPal());
        payPalProcessor.processPayment(300.0);
    }
}