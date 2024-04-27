import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * https://stackoverflow.com/questions/12318488/adding-a-chartpanel-to-jpanel/12318835#12318835
 */
public class JPlot {
    public static void main(String[] args) {

        PlotSaltSmooth plotter = new PlotSaltSmooth();
        ArrayList<Double> data = plotter.plotData(-100, 100, 1);
        ArrayList<Double> salted = plotter.saltValues(-2500,2500);
        ArrayList<Double> smoothed = plotter.smoother(15);
        ArrayList<Integer> xValues = plotter.getX();

         // Create XYSeries for original data
         XYSeries originalSeries = new XYSeries("Original Data");
         for (int i = 0; i < data.size(); i++)
             originalSeries.add(xValues.get(i), data.get(i));
 
         // Create XYSeries for salted data
         XYSeries saltedSeries = new XYSeries("Salted Data");
         for (int i = 0; i < salted.size(); i++)
             saltedSeries.add(xValues.get(i), salted.get(i));
 
         // Create XYSeries for smoothed data
         XYSeries smoothedSeries = new XYSeries("Smoothed Data");
         for (int i = 0; i < smoothed.size(); i++)
             smoothedSeries.add(xValues.get(i), smoothed.get(i));
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(originalSeries);
        dataset.addSeries(saltedSeries);
        dataset.addSeries(smoothedSeries);
        JFreeChart chart = ChartFactory.createXYLineChart("x^2 + 13", "X Values", "Y Values", dataset, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setDomainZoomable(true);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(chartpanel, BorderLayout.NORTH);

        JFrame frame = new JFrame();
        frame.add(jPanel4);
        frame.pack();
        frame.setVisible(true);
    }
}