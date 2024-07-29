public class Logger {
    private static Logger instance;
    private Logger(){
    }
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    public void log(String message) {
        System.out.println("Logging: " + message);
    }
    public static void main(String[] args) {
        Logger l1 = Logger.getInstance();
        Logger l2 = Logger.getInstance();

        System.out.println(l1 == l2);

        l1.log("Log entry 1");
        l2.log("Log entry 2");
    }

}
