import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class ChartController implements Controller {

    private MainWindow view;
    private PointsTableModel model;

    public ChartController(PointsTableModel model) {
        this.model = model;
        try
        {
            SwingUtilities.invokeAndWait(() -> EventQueue.invokeLater(() -> {
                this.view = new MainWindow(800, 600, model, this);
                view.setVisible(true);
            }));
        } catch (InvocationTargetException | InterruptedException e) {
            System.out.println("FAIL!");
        }


        }@Override
    public void addPoint(float x) {
        model.addPoint(x);
    }

    @Override
    public void addPoint(Point point) {
        model.addPoint(point);
    }

    @Override
    public float getY(float x) {
        return x * x;
    }

//    @Override
//    public void runApp() {
//        view.setVisible(true);
//    }

    @Override
    public void removePoint(int idx) {
        model.removePoint(idx);
    }

    @Override
    public void changePoint(int idx, float changedX) {
        model.changePoint(idx, new Point(changedX, getY(changedX)));
    }

    @Override
    public DefaultCategoryDataset getDataset() {
        return model.toDataset();
    }

    @Override
    public void addListener(TableModelListener listener) {
        this.model.addTableModelListener(listener);
    }
}
