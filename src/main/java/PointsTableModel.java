import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.*;

public class PointsTableModel implements TableModel, Observable {

    private List<Point> points = new ArrayList<>();
    private Set<TableModelListener> listeners = new HashSet<>();
    private List<Observer> observers = new ArrayList<>();

    public PointsTableModel() {
//        addPoint(1, 1);
//        addPoint(2, 4);
//        addPoint(3, 9);
        notifyAllObservers();
    }

    public void addPoint(float x, float y) {
        points.add(new Point(x, y));
        notifyAllObservers();
    }

    public void removePoint(float x, float y) {
        points.remove(new Point(x, y));
        notifyAllObservers();
    }



    @Override
    public int getRowCount() {
        return points.size();
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
        }
        notifyAllObservers();
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
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
            observer.handleEvent(this);
        }
    }

    public DefaultCategoryDataset toDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String LEGEND = "y = x^2";
        points.forEach(point -> dataset.addValue((Number) point.getY(), LEGEND, point.getX()));
        return dataset;
    }
}
