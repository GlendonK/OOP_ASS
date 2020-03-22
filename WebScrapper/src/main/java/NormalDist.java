import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class NormalDist extends JFrame {

    protected String databaseName;

    public NormalDist(String applicationTitle, String catergory, String databaseName) throws Exception {
        super(applicationTitle);

        this.databaseName = databaseName;

        CallDatabase callDatabase = new CallDatabase();
        Calculations cal = new Calculations(callDatabase.query(databaseName)); // GET DATA FROM DATABSE

        ////// INPUT DATA VALUES TO PLOT GRAPH ////////
        Function2D normal = new NormalDistributionFunction2D(cal.getMean(), cal.getSD());
        XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, (cal.getMin()-20), (cal.getMax()+20), cal.getSize(), catergory);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                applicationTitle, "Price", "Frequency", dataset, PlotOrientation.VERTICAL,
                true, true, false);



        // THIS CODE PLOT LQR AND UQR AND MEAN
        ValueMarker lqr = new ValueMarker(cal.getLQR());
        lqr.setPaint(Color.red);
        lqr.setLabel("Lower Quartile");
        lqr.setLabelPaint(Color.red);
        XYPlot plotLqr = (XYPlot) chart.getPlot();
        plotLqr.addDomainMarker(lqr);

        ValueMarker hqr = new ValueMarker(cal.getUQR());
        hqr.setPaint(Color.red);
        hqr.setLabel("Upper Quartile");
        hqr.setLabelPaint(Color.red);
        XYPlot plotHqr = (XYPlot) chart.getPlot();
        plotHqr.addDomainMarker(hqr);
        ///////////////////////////////////////////////

        ValueMarker mean = new ValueMarker(cal.getMean());
        mean.setPaint(Color.red);
        mean.setLabel("Mean");
        mean.setLabelPaint(Color.red);
        XYPlot plotmean = (XYPlot) chart.getPlot();
        plotmean.addDomainMarker(mean);

        NumberAxis rangeAxis = (NumberAxis) plotmean.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());



        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        setContentPane(chartPanel);
    }

}

