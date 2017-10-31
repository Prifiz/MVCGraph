public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new ChartController(view);
        controller.runApp();
    }
}
