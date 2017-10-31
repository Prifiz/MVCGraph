public class ChartController implements Controller {

    private View view;
    //private Model model;


    public ChartController(View view) {
        this.view = view;
    }

    @Override
    public void addPoint(float x) {

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
    public void removePoint(float x) {

    }
}
