import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements Observer {

    private JFreeChart lineChart;
    private ChartPanel chartPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultCategoryDataset dataset;

    private PointsTableModel model;
    private ChartController controller;

    public MainWindow(int width, int height, PointsTableModel model, ChartController controller) throws HeadlessException {
        this.model = model;
        this.controller = controller;
        this.dataset = model.toDataset();
        model.registerObserver(this);
//        model.addTableModelListener(e -> {
//
//        });
        setTitle("MVC Lab");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.updateUI();
        setLocationRelativeTo(null);
        setSize(width, height);
        setResizable(false);
        lineChart = createChart();
        chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize(new Dimension(width, height/2));
        initLayout();
        pack();

    }

    public void updateChart(DefaultCategoryDataset dataset) {

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
                .addComponent(scrollPane));

        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createSequentialGroup()
                .addComponent(chartPanel)
                .addComponent(scrollPane));

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
