import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;

public interface Controller {
    void addPoint(float x);
    void addPoint(Point point);
    float getY(float x);
    //void runApp();
    void removePoint(int idx);
    void changePoint(int idx, float changedX);
    DefaultCategoryDataset getDataset();
    void addListener(TableModelListener listener);
}
