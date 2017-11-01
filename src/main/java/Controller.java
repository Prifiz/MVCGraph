import org.jfree.data.category.DefaultCategoryDataset;

public interface Controller {
    void addPoint(float x);
    void addPoint(Point point);
    float getY(float x);
    void runApp();
    void removePoint(int idx);
    void changePoint(int idx, float changedX);
    DefaultCategoryDataset getDataset();
}
