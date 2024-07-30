public class FinancialForecasting{
    public static double computeFutureValue(double initialAmount, double annualRate, int years) {

        if (years == 0) {
            return initialAmount;
        }

        return computeFutureValue(initialAmount * (1 + annualRate), annualRate, years - 1);
    }

    public static void main(String[] args) {
        double initialAmount = 1000.0;
        double annualRate = 0.05;
        int years = 10;

        double futureValue = computeFutureValue(initialAmount, annualRate, years);
        System.out.println("The future value of the investment is: Rs" + String.format("%.2f", futureValue));
    }
}