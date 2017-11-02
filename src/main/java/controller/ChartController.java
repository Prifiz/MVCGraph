package controller;

import model.Model;
import org.jfree.data.category.DefaultCategoryDataset;
import view.MainWindow;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import model.Point;
import view.View;

public class ChartController implements Controller {

    private View view;
    private Model model;

    public ChartController(Model model) {
        this.model = model;
        try {
            SwingUtilities.invokeAndWait(() -> EventQueue.invokeLater(() -> {
                this.view = new MainWindow(800, 600, model, this);
                view.launch();
            }));
        } catch (InvocationTargetException | InterruptedException e) {
            System.out.println("FAIL!");
        }
    }

    @Override
    public void addPoint(float x) {
        model.addPoint(x);
    }

    @Override
    public void addPoint(Point point) {
        model.addPoint(point);
    }

    @Override
    public void removePoint(int idx) {
        model.removePoint(idx);
    }

    @Override
    public DefaultCategoryDataset getDataset() {
        return model.toDataset();
    }

    @Override
    public void addListener(TableModelListener listener) {
        ((AbstractTableModel)this.model).addTableModelListener(listener);
    }
}
