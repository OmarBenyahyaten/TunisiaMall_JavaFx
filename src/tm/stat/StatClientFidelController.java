package tm.stat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tm.stat.StatClientFidele;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import java.util.Date;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import tm.stat.StatClientFidele;

/**
 * FXML Controller class
 *
 * @author mounir
 */
public class StatClientFidelController implements Initializable {
      @FXML
    CategoryAxis xAxis = new CategoryAxis();
    @FXML
    NumberAxis yAxis = new NumberAxis();
    @FXML
    LineChart lc = new LineChart(xAxis, yAxis);

    StatClientFidele p = new StatClientFidele();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        lc.getData().addAll(p.clientFidel_Stat());
        lc.setTitle("nombre de client fidelek par aneee");
        lc.setAnimated(true); 
        lc.getXAxis().setLabel("Year");
        lc.getYAxis().setLabel("Client fidele");
        lc.getData().addAll(p.getChartData());
    }

}
