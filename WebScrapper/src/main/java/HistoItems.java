import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.IntervalXYDataset;
import javax.swing.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class HistoItems extends JFrame{

    private String databaseName1;
    private String databaseName2;
    private String databaseName3;
    private String applicationTitle;

    public HistoItems(String applicationTitle, String databaseName1, String databaseName2, String databaseName3) throws Exception {
        super(applicationTitle);
        this.applicationTitle = applicationTitle;
        this.databaseName1 = databaseName1;
        this.databaseName2 = databaseName2;
        this.databaseName3 = databaseName3;

        ///////// CREATE THE GRAPH LAYOUT //////////////////
        IntervalXYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);

    }

     ///////////// INPUT DATA /////////////////////////////
    public IntervalXYDataset createDataset() throws Exception {
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);

        CallDatabase callDatabaseCarousell = new CallDatabase();

        /////////// QUERY DATABASE TO GET DATA AND INPUT INTO GRAPH /////////////////
        ///////////////// CAROUSELL DATA /////////////////////////
        Calculations calCarousell = new Calculations(callDatabaseCarousell.query(databaseName1));
        ArrayList<Float> socksCarousell = calCarousell.arraylist;
        double[] data1 = new double[socksCarousell.size()];
        for (int i = 0; i<socksCarousell.size(); i++){
            data1[i] = socksCarousell.get(i);
        }
        dataset.addSeries(databaseName1, data1, data1.length, calCarousell.getMin(), calCarousell.getMax());

        ///////// Qoo10 DATA ///////////////////////////////
        CallDatabase callDatabaseQoo10 = new CallDatabase();
        Calculations calQoo10 = new Calculations(callDatabaseQoo10.query(databaseName2));
        ArrayList<Float> socksQoo10 = calQoo10.arraylist;
        double[] data2 = new double[socksQoo10.size()];
        for (int i = 0; i<socksQoo10.size(); i++){
            data2[i] = socksQoo10.get(i);
        }
        dataset.addSeries(databaseName2, data2, data2.length, calQoo10.getMin(), calQoo10.getMax());

        ///////////// RAKUTEN DATA //////////////////////////
        CallDatabase callDatabaseRakuten = new CallDatabase();
        Calculations calRakuten = new Calculations(callDatabaseRakuten.query(databaseName3));
        ArrayList<Float> socksRakuten = calRakuten.arraylist;
        double[] data3 = new double[socksRakuten.size()];
        for (int i = 0; i<socksRakuten.size(); i++){
            data3[i] = socksRakuten.get(i);
        }
        dataset.addSeries(databaseName3, data3, data2.length, calRakuten.getMin(), calRakuten.getMax());

        return dataset;
    }

    /////////////// MAKE THE CHART ////////////////////////////
    public JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                applicationTitle,"Price", "Frequency", dataset, PlotOrientation.VERTICAL,
                true, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.75f);
        NumberAxis axis = (NumberAxis) plot.getDomainAxis();
        axis.setAutoRangeIncludesZero(false);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());

        return chart;
    }
}
