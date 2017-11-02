package model;

import org.jfree.data.category.DefaultCategoryDataset;

public interface Model {
    void addPoint(Point point);
    void addPoint(float x);
    void removePoint(int idx);
    DefaultCategoryDataset toDataset();
}
