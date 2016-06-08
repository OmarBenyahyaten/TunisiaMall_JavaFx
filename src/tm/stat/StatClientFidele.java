/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import tm.technique.DataSource;

/**
 *
 * @author mounir
 */
public class StatClientFidele {

    public Connection conn = DataSource.getInstance().getConnection();

    public XYChart.Series enseigne_Stat() {

        String req = "select count(*) AS nombre,extract(year from `dateCreationEnseigne`) as annee from enseigne group by extract(year from `dateCreationEnseigne`) order by `dateCreationEnseigne`";

        XYChart.Series aSeries = new XYChart.Series();
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                aSeries.getData().add(new XYChart.Data(rs.getString("annee"), rs.getInt("nombre")));

            }
            return aSeries;

        } catch (SQLException ex) {
            Logger.getLogger(StatClientFidele.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<String> Get_Enseigne() {
        ArrayList<String> enseigne = new ArrayList<>();
        String req = "select nom from enseigne";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                enseigne.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatClientFidele.class.getName()).log(Level.SEVERE, null, ex);
        }

        return enseigne;

    }

    public ObservableList<XYChart.Series<String, Double>> getChartData() {
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        StatClientFidele p = new StatClientFidele();

        ArrayList<String> enseigne = new ArrayList<>();
        enseigne = p.Get_Enseigne();

        for (String enseigne1 : enseigne) {
            XYChart.Series aSeries = new XYChart.Series();

            aSeries.setName(enseigne1);
            System.out.println("enseigne 1 " + enseigne1);
            String req = "SELECT count(*) as nombre,extract(year from `dateCreationCarteFidelite`) as dateCreation FROM cartefidelite ,enseigne "
                    + "where cartefidelite.idEnseigne=enseigne.idEnseigne AND enseigne.nom=?"
                    + "group by extract(year from `dateCreationCarteFidelite`)";
            try {
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setString(1, enseigne1);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    aSeries.getData().add(new XYChart.Data(rs.getString("dateCreation"), rs.getInt("nombre")));

                    System.out.println(rs.getString("dateCreation") + "------" + rs.getInt("nombre"));

                }
                answer.add(aSeries);

            } catch (SQLException ex) {
                Logger.getLogger(StatClientFidele.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return answer;
    }

    public ObservableList<Data> sommeVente(String date) {
        StatClientFidele p = new StatClientFidele();
        ObservableList list = FXCollections.observableArrayList();
        ArrayList<String> enseigne = new ArrayList<>();
        enseigne = p.Get_Enseigne();
//for (String enseigne1 : enseigne) {
//
//    System.out.println(enseigne1);
//}

        String req = "SELECT sum(TotalTTC) as total,facture.dateAchat,stock.enseigne as nom from vente  join facture on (vente.idFacture=facture.idFacture) "
                + "join stock on (stock.idArticle=vente.idArticle) WHERE(extract(year from facture.dateAchat)=?) group by stock.enseigne ";
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement(req);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString("nom"), rs.getInt("total")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatClientFidele.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<String> list_date() {
        List list = new ArrayList();
        String req="SELECT DISTINCT extract(year FROM dateAchat) as annee FROM facture";
         PreparedStatement ps;
        try {
            ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("annee"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatClientFidele.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
