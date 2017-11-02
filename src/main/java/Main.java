import controller.ChartController;
import controller.Controller;
import model.Model;
import model.PointsTableModel;

public class Main {
    public static void main(String[] args) {
        Model model = new PointsTableModel();
        Controller controller = new ChartController(model);
        final float INITIAL_MAX_X = 15.0f;
        for (float x = -INITIAL_MAX_X; x <= INITIAL_MAX_X; x++) {
            controller.addPoint(x);
        }
    }
}