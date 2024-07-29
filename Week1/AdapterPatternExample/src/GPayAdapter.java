public class GPayAdapter implements PaymentProcessor {
    private GPay gPay;

    public GPayAdapter(GPay gPay) {
        this.gPay = gPay;
    }

    @Override
    public void processPayment(double amount) {
        gPay.makePayment(amount);
    }
}