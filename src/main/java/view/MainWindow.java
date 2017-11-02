package view;

import controller.Controller;
import model.EmptyPoint;
import model.Model;
import model.Observable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class MainWindow extends JFrame implements Observer, TableModelListener, View {

    private JFreeChart lineChart;
    private ChartPanel chartPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton addPointButton;
    private JButton removePointButton;

    private Model model;
    private Controller controller;

    public MainWindow(int width, int height, Model model, Controller controller)
            throws HeadlessException {
        this.addPointButton = new JButton("Add Point");
        this.removePointButton = new JButton("Remove Point");
        this.model = model;
        this.controller = controller;
        ((Observable)model).registerObserver(this);

        initFrame(width, height);
        initChart(width, height);
        initTable();
        controller.addListener(this);
        initButtonsActions();
        initLayout();

        pack();
    }

    private void initFrame(int width, int height) {
        setTitle("MVC Lab");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(width, height);
        setResizable(false);
    }

    private void initChart(int width, int height) {
        lineChart = createChart();
        chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize(new Dimension(width, height/2));
    }

    private void initTable() {
        table = new JTable((AbstractTableModel)model);
        scrollPane = new JScrollPane(table);
        scrollPane.updateUI();
    }

    private void initButtonsActions() {
        addPointButton.addActionListener(e -> {
            controller.addPoint(new EmptyPoint());
            table.updateUI();
            refreshChart();
        });
        removePointButton.addActionListener(e -> {
            controller.removePoint(table.getSelectedRow());
            table.updateUI();
            refreshChart();
        });
    }

    private void initLayout() {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(chartPanel)
                .addComponent(scrollPane)
                .addGroup(groupLayout.createSequentialGroup())
                    .addComponent(addPointButton)
                    .addComponent(removePointButton));

        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createSequentialGroup()
                .addComponent(chartPanel)
                .addComponent(scrollPane)
                .addGroup(groupLayout.createParallelGroup())
                    .addComponent(addPointButton)
                    .addComponent(removePointButton));

        groupLayout.setVerticalGroup(vGroup);
    }

    private void refreshChart() {
        lineChart = createChart();
        chartPanel.setChart(lineChart);
        chartPanel.updateUI();
    }

    private JFreeChart createChart() {
        return ChartFactory.createLineChart(
                "Function chart",
                "x","y",
                controller.getDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);
    }

    @Override
    public void handleEvent() {
        table.updateUI();
        refreshChart();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("CHANGED!");
    }

    @Override
    public void launch() {
        setVisible(true);
    }
}
