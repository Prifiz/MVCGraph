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
        return 0;
    }

    @Override
    public void runApp() {
        view.setVisible(true);
    }
}
