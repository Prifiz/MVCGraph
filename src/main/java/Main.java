public class Main {
    public static void main(String[] args) {
        PointsTableModel model = new PointsTableModel();
        Controller controller = new ChartController(model);
        controller.addPoint(1);
        controller.addPoint(3);
        controller.addPoint(2);
        //controller.runApp();
    }
}