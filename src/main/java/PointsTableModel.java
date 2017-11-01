import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.*;

public class PointsTableModel implements TableModel, Observable {

    private List<Point> points;
    private Set<TableModelListener> listeners;
    private List<Observer> observers;

    public PointsTableModel() {
        points = new ArrayList<>();
        listeners = new HashSet<>();
        observers = new ArrayList<>();
        notifyAllObservers();
    }

    public void addPoint(Point point) {
        points.add(point);
        if(!point.isEmpty()) {
            notifyAllObservers();
        }
    }

    public void removePoint(int idx) {
        Point pointToRemove = points.get(idx);
        points.remove(idx);
        if(!pointToRemove.isEmpty()) {
            notifyAllObservers();
        }
    }

    public void changePoint(int idx, Point changedPoint) {
        points.get(idx).setX(changedPoint.getX());
        points.get(idx).setY(changedPoint.getY());
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
        } else {
            points.get(rowIndex).setY((Float)aValue);
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

    private void fireTableChanged(TableModelEvent e) {
        // Guaranteed to return a non-null array

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.size() - 2; i >= 0; i -= 2) {
            if (listeners.toArray()[i] == TableModelListener.class) {
                ((TableModelListener)listeners.toArray()[i + 1]).tableChanged(e);
            }
        }
    }
}
