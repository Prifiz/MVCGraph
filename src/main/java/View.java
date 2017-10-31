import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {



    public View() throws HeadlessException {
        setTitle("MVC Lab");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(800, 600);
        setResizable(false);

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Test",
                "x","y",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String LEGEND = "y = x^2";
        dataset.addValue( 15 , LEGEND , "1970" );
        dataset.addValue( 30 , LEGEND , "1980" );
        dataset.addValue( 60 , LEGEND ,  "1990" );
        dataset.addValue( 120 , LEGEND , "2000" );
        dataset.addValue( 240 , LEGEND , "2010" );
        dataset.addValue( 300 , LEGEND , "2014" );
        return dataset;
    }
}
