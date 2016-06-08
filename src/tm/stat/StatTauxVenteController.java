/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import tm.stat.StatClientFidele;

/**
 * FXML Controller class
 *
 * @author mounir
 */
public class StatTauxVenteController implements Initializable {

    @FXML
    private PieChart chart;
    @FXML
    private TextField textField;
    StatClientFidele model = new StatClientFidele();
    String date="2016";
@FXML private ComboBox comb;
    private ObservableList<PieChart.Data> pcData = model.sommeVente(date);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comb.getItems().addAll(model.list_date());
                comb.getSelectionModel().selectFirst();
        chart.setTitle("taux de vente par l annee "+comb.getValue().toString());
        pcData = model.sommeVente(date);
            chart.setData(pcData);
        setupAnimation();
    }
    @FXML
private void test(){
    pcData = model.sommeVente(comb.getValue().toString());
            chart.setData(pcData);
        setupAnimation();
}
    private void resetTextField() {
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000), textField);
        ft.setToValue(0.0);
        ft.playFromStart();
        ft.setOnFinished(event -> {
            textField.setTranslateX(0);
            textField.setTranslateY(0);
        });
    }

    private void setupAnimation() {
        pcData.stream().forEach(pieData -> {
            System.out.println(pieData.getName() + ": " + pieData.getPieValue());
            pieData.getNode().addEventHandler(
                    MouseEvent.MOUSE_CLICKED, event -> {
                        if (event.isControlDown()) {
                            // Move the textfield to where the mouse click is
                            textField.setTranslateX(event.getSceneX() - textField.getLayoutX());
                            textField.setTranslateY(event.getSceneY() - textField.getLayoutY());
                            textField.setText(String.valueOf(pieData.getPieValue()));
                            textField.setOpacity(1.0);
                            textField.setOnAction(evt -> {
                                try {
                                    System.out.println("You entered: " + textField.getText());
                                    final Double num = Double.valueOf(textField.getText());
                                    if (num > 0) {
                                        pieData.setPieValue(num);
                                        resetTextField();
                                    } else {
                                        textField.setText(String.valueOf(pieData.getPieValue()));
                                    }
                                } catch (NumberFormatException e) {
                                    // Just use the original number if the format is bad
                                    textField.setText(String.valueOf(pieData.getPieValue()));
                                }
                            });
                        } else {
                            resetTextField();
                            pieData.getNode().setTranslateX(0);
                            pieData.getNode().setTranslateY(0);
                            Bounds b1 = pieData.getNode().getBoundsInLocal();
                            double newX = (b1.getWidth()) / 2 + b1.getMinX();
                            double newY = (b1.getHeight()) / 2 + b1.getMinY();
                            // Make sure pie wedge location is reset
                            pieData.getNode().setTranslateX(0);
                            pieData.getNode().setTranslateY(0);
                            // Create the animation
                            TranslateTransition tt = new TranslateTransition(
                                    Duration.millis(1500), pieData.getNode());
                            tt.setToX(newX);
                            tt.setToY(newY);
                            tt.setAutoReverse(true);
                            tt.setCycleCount(2);
                            tt.playFromStart();
                        }
                    });
        });
    }

}
