package MainProgram;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

public final class GenGraphic extends JFrame {
    ChartPanel chartPanel = null;
    XYSeriesCollection _dataset = null;
    private ActionEvent event;
    String _title = "";
    public GenGraphic(XYSeriesCollection data, String title) {
        this._title = title;
        this._dataset = data;
        this.chartPanel = createChartPanel();
    }

    public ChartPanel createChartPanel() {
        String chartTitle = this._title;
        String xAxisLabel = "Iterations";
        String yAxisLabel = "Fitness";

        XYDataset dataset = this._dataset;

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                           xAxisLabel, yAxisLabel, dataset);
        ChartPanel chPanel = new ChartPanel(chart);

        chPanel.setPreferredSize(new Dimension(785, 440));
        
        final XYPlot plot = chart.getXYPlot();
        
        for(int i = 0; i < this._dataset.getSeriesCount(); i++){
            final Marker start = new ValueMarker(this._dataset.getSeries(i).getMaxY());            
            start.setLabel("BestFitness: "+this._dataset.getSeries(i).getMaxY());
            start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
            start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
            plot.addRangeMarker(start);            
        }
        return chPanel;
    }
}