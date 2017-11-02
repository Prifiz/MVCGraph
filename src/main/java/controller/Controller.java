package controller;

import model.Point;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.TableModelListener;

public interface Controller {
    void addPoint(float x);
    void addPoint(Point point);
    void removePoint(int idx);
    DefaultCategoryDataset getDataset();
    void addListener(TableModelListener listener);
}
