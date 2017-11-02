package model;

import org.jfree.data.category.DefaultCategoryDataset;
import view.Observer;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.*;

public class PointsTableModel extends AbstractTableModel implements TableModel, Observable, Model {

    private List<Point> points;
    private List<Observer> observers;

    public PointsTableModel() {
        points = new ArrayList<>();
        observers = new ArrayList<>();
        notifyAllObservers();
    }

    private float calcFunctionValue(float x) {
        return x*x;
    }

    public void addPoint(Point point) {
        points.add(point);
        Collections.sort(points);
        if(!point.isEmpty()) {
            notifyAllObservers();
        }
    }

    public void addPoint(float x) {
        Point point = new Point(x, calcFunctionValue(x));
        addPoint(point);
    }

    public void removePoint(int idx) {
        Point pointToRemove = points.get(idx);
        points.remove(idx);
        Collections.sort(points);
        if(!pointToRemove.isEmpty()) {
            notifyAllObservers();
        }
    }

    @Override
    public int getRowCount() {
        if(points==null) {
            return 0;
        } else {
            return points.size();
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == 0) {
            return "X";
        } else {
            return "Y";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Float.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return points.get(rowIndex).getX();
        } else {
            return points.get(rowIndex).getY();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            points.get(rowIndex).setX(Float.valueOf((String) aValue));
            points.get(rowIndex).setY(calcFunctionValue(Float.valueOf((String)aValue)));
            points.get(rowIndex).setEmpty(false);
        }
        if(columnIndex == 1) {
            points.get(rowIndex).setY(Float.valueOf((String)aValue));
            points.get(rowIndex).setEmpty(false);
        }
        Collections.sort(points);

        notifyAllObservers();
        fireTableDataChanged();
    }

    @Override
    public void registerObserver(view.Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for(Observer observer : observers) {
            observer.handleEvent();
        }
    }

    public DefaultCategoryDataset toDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String LEGEND = "y = x^2";
        points.forEach(point -> {
            if(!point.isEmpty()) {
                dataset.addValue((Number) point.getY(), LEGEND, point.getX());
            }
        });
        return dataset;
    }
}
