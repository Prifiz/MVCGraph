import org.jfree.data.category.DefaultCategoryDataset;

public class ChartController implements Controller {

    private MainWindow view;
    private PointsTableModel model;

    public ChartController(PointsTableModel model) {
        this.model = model;
        this.view = new MainWindow(800, 600, model, this);
    }

    @Override
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

    @Override
    public void runApp() {
        view.setVisible(true);
    }

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
}
