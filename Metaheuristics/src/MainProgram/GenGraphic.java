package MainProgram;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
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
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYItemRenderer;

public final class GenGraphic extends JFrame {
    ChartPanel chartPanel = null;
    XYSeriesCollection _dataset = null;
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
        if(this._title.equals("GeneticAlgorithm")){
            chart = ChartFactory.createScatterPlot(chartTitle,xAxisLabel, yAxisLabel, dataset);
            ChartPanel chPanel = new ChartPanel(chart);

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
            //Modificación tamaño de puntos en el grafico
            XYItemRenderer renderer = plot.getRenderer();
            renderer.setSeriesPaint(0,Color.blue);
            double size = 2.0;
            double delta = 2;
            Shape shape1 = new Rectangle2D.Double(-delta, -delta, size, size);
            Shape shape2 = new Ellipse2D.Double(-delta, -delta, size, size);
            renderer.setSeriesShape(0, shape1);
            renderer.setSeriesShape(1, shape2);

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
            
            NumberAxis rangeAxis = (NumberAxis)plot.getDomainAxis();
            if(this._dataset.getSeriesCount()>2){
                rangeAxis.setRange(0.0, 6000);
            }
            else{
                if(this._dataset.getSeries(1).getDescription().equals("bestACO")){
                    rangeAxis.setRange(0.0, 1500);
                }
            }
            ArrayList<Color> colores = new ArrayList<>();
            colores.add(new Color(250,46,2));
            colores.add(new Color(2,179,250));
            colores.add(new Color(3,151,84));
            colores.add(new Color(242,193,8));
            
            for(int i = 0; i < this._dataset.getSeriesCount(); i++){
                final Marker start = new ValueMarker(this._dataset.getSeries(i).getMaxY());  
                if(this._dataset.getSeries(i).getDescription() != null){
                    start.setLabel(this._dataset.getSeries(i).getDescription()+": "+this._dataset.getSeries(i).getMaxY());
                    start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
                    Font font = new Font("TimesRoman", Font.PLAIN ,12);
                    start.setLabelFont(font);
                    start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                    plot.addRangeMarker(start);                   
                    plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(i, colores.get(i));
                }
            }
            return chPanel;            
        }                
    }

}