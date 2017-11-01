import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;

public class MainWindow extends JFrame implements Observer {

    private JFreeChart lineChart;
    private ChartPanel chartPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultCategoryDataset dataset;
    private JButton addPointButton;
    private JButton removePointButton;

    private PointsTableModel model;
    private ChartController controller;

    public MainWindow(int width, int height, PointsTableModel model, ChartController controller)
            throws HeadlessException {
        this.addPointButton = new JButton("Add Point");
        this.removePointButton = new JButton("Remove Point");
        this.model = model;
        this.controller = controller;
        this.dataset = model.toDataset();
        model.registerObserver(this);

        initFrame(width, height);
        initChart(width, height);
        initTable();
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
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.updateUI();

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                PointsTableModel model = (PointsTableModel) e.getSource();
                Object data = model.getValueAt(row, column);
                controller.changePoint(row, (Float) data);
            }
        });
    }

    private void initButtonsActions() {
        addPointButton.addActionListener(e -> {
            controller.addPoint(new EmptyPoint());
            table.updateUI();
        });
        removePointButton.addActionListener(e -> {
            controller.removePoint(table.getSelectedRow());
            table.updateUI();
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
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false);
    }

    @Override
    public void handleEvent(PointsTableModel model) {
        this.dataset = model.toDataset();
        refreshChart();
    }
}
