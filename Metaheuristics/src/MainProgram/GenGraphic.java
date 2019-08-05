package MainProgram;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import java.awt.Font;

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
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);

        for(int i = 0; i < this._dataset.getSeriesCount(); i++){
            final Marker start = new ValueMarker(this._dataset.getSeries(i).getMaxY());            
            start.setLabel(this._dataset.getSeries(i).getDescription()+": "+this._dataset.getSeries(i).getMaxY());
            start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
            Font font = new Font("TimesRoman", Font.PLAIN ,12);
            start.setLabelFont(font);
            start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
            plot.addRangeMarker(start);            
        }
        return chPanel;
    }
}