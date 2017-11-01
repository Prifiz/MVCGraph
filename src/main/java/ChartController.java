public class ChartController implements Controller {

    private MainWindow view;
    private PointsTableModel model;


    public ChartController(PointsTableModel model) {
        this.model = model;
        this.view = new MainWindow(800, 600, model, this);
    }

    private void updateChart() {

    }

    @Override
    public void addPoint(float x) {
        model.addPoint(new Point(x, getY(x)));
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
}
