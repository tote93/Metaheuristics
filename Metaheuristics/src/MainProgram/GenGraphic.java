package MainProgram;


import java.awt.BasicStroke;
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
import org.jfree.chart.axis.NumberAxis;

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

        JFreeChart chart;
        if(this._title.equals("TestingGA")){//GeneticAlgorithm
            chart = ChartFactory.createScatterPlot(chartTitle,
                           xAxisLabel, yAxisLabel, dataset);
            ChartPanel chPanel = new ChartPanel(chart);
            chPanel.setPreferredSize(new Dimension(785, 440));

            final XYPlot plot = (XYPlot) chart.getPlot();
            plot.setDomainPannable(true);
            plot.setRangePannable(true);
            plot.setDomainZeroBaselineVisible(true);
            plot.setRangeZeroBaselineVisible(true);

            plot.setDomainGridlineStroke(new BasicStroke(0.0f));
            plot.setDomainMinorGridlineStroke(new BasicStroke(0.0f));
            plot.setDomainGridlinePaint(Color.blue);
            plot.setRangeGridlineStroke(new BasicStroke(0.0f));
            plot.setRangeMinorGridlineStroke(new BasicStroke(0.0f));
            plot.setRangeGridlinePaint(Color.blue);

            plot.setDomainMinorGridlinesVisible(true);
            plot.setRangeMinorGridlinesVisible(true);

            NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
            //domainAxis.setAutoRangeIncludesZero(false);
            for(int i = 0; i < this._dataset.getSeriesCount(); i++){
                final Marker start = new ValueMarker(this._dataset.getSeries(i).getMaxY());  
                if(this._dataset.getSeries(i).getDescription() != null){
                    start.setLabel(this._dataset.getSeries(i).getDescription()+": "+this._dataset.getSeries(i).getMaxY());
                    start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
                    Font font = new Font("TimesRoman", Font.PLAIN ,12);
                    start.setLabelFont(font);
                    start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                    plot.addRangeMarker(start);                    
                }
            }
            return chPanel;             
        }
        else{
            chart  = ChartFactory.createXYLineChart(chartTitle,
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
                if(this._dataset.getSeries(i).getDescription() != null){
                    start.setLabel(this._dataset.getSeries(i).getDescription()+": "+this._dataset.getSeries(i).getMaxY());
                    start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
                    Font font = new Font("TimesRoman", Font.PLAIN ,12);
                    start.setLabelFont(font);
                    start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                    plot.addRangeMarker(start);                    
                }
            }
            return chPanel;            
        }                
    }
}