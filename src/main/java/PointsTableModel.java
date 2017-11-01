import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.TableModelListener;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.*;

public class PointsTableModel extends AbstractTableModel implements TableModel, Observable {

    private List<Point> points;
    private Set<TableModelListener> listeners;
    private List<Observer> observers;

    public PointsTableModel() {
        points = new ArrayList<>();
        listeners = new HashSet<>();
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

    public void changePoint(int idx, Point changedPoint) {
        points.get(idx).setX(changedPoint.getX());
        points.get(idx).setY(changedPoint.getY());
        Collections.sort(points);
        notifyAllObservers();
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
            points.get(rowIndex).setX((Float)aValue);
            points.get(rowIndex).setY(calcFunctionValue((Float)aValue));
            points.get(rowIndex).setEmpty(false);
//            points.get(rowIndex).setY(calcFunctionValue((Float)aValue));
//        } else {
//            points.get(rowIndex).setY((Float)aValue);
        }
        if(columnIndex == 1) {
            points.get(rowIndex).setY(((Float)aValue));
            points.get(rowIndex).setEmpty(false);
        }
        Collections.sort(points);

        notifyAllObservers();
        fireTableDataChanged();
        //fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
        //notifyAllObservers();
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    @Override
    public void registerObserver(Observer observer) {
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
