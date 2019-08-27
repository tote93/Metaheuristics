/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainProgram;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;
import com.orsoncharts.style.StandardChartStyle;
import com.orsoncharts.table.TextElement;
import com.orsoncharts.util.Orientation;
import java.awt.Font;
import java.util.ArrayList;

/**
 *
 * @author i62gorej
 */
public class GenGraphic3D{
    XYZDataset<String> _dataset = null;
    String _funct = "";
    ArrayList<Double> _ejes = new ArrayList<>();
    public GenGraphic3D(XYZDataset<String> dataset, String functionType, ArrayList<Double> array) {
        _dataset = dataset;
        _funct = functionType;
        _ejes = array;
    }
    public Chart3D create3DGraph(){
        Chart3D chart = Chart3DFactory.createScatterChart(null, null, 
               this._dataset, "EjeX", "EjeY", "EjeZ");
        String cad = "Function: "+this._funct+"                 "
                + "Best Values: X = "+_ejes.get(0) +" Y = "+_ejes.get(1)+" Z = "+_ejes.get(2);
        TextElement title1 = new TextElement(cad);
        title1.setFont(StandardChartStyle.createDefaultFont(Font.BOLD, 28));
        chart.setTitle(title1);
        chart.setLegendAnchor(LegendAnchor.BOTTOM_LEFT);
        
        chart.setLegendOrientation(Orientation.VERTICAL);
        XYZPlot plot = (XYZPlot) chart.getPlot();       
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        
        
        renderer.setColors(Colors.createIntenseColors());
        chart.setViewPoint(ViewPoint3D.createAboveViewPoint(25));
        return chart;        
    }        
}
