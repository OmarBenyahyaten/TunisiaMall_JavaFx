/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.StackedBarChart;
import tm.stat.StatClientFidele;

/**
 *
 * @author mounir
 */


/**
 *
 * @author mounir
 */
public class StatNouvelEnseigneController implements Initializable{
    private static final int AVAILABLE_COLORS = 10;
    private static final int CASPIAN_COLOR_COUNTS = 8;
     @FXML  StackedBarChart  bar;
      
    StatClientFidele p = new StatClientFidele();
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    bar.setTitle("nombre enseigne");
           bar.setStyle("-fx-background-color: rgba(0,168,355,0.05);-fx-background-radius: 10;");

        bar.getXAxis().setLabel("annee enseigne");
        bar.getYAxis().setLabel("nombre)");
     int i=0;
   
        
            for (Node node : bar.lookupAll(".series" + i)) {
                node.getStyleClass().remove("default-color" + (i % CASPIAN_COLOR_COUNTS));
                node.getStyleClass().add("default-color" + (i % AVAILABLE_COLORS));
            }
        
        
        afficher();
       
    }

    void afficher() {
       
 bar.getData().add(p.enseigne_Stat()); 
    }
    
}

