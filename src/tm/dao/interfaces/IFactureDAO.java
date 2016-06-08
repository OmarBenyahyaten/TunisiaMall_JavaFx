/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import tm.entities.Facture;
import tm.entities.Stock;

/**
 *
 * @author omarblythe
 */
public interface IFactureDAO  {
     void insertFacture(Facture facture);

    void updateFacture(Facture facture);

    void deleteFacture(int id);

 ObservableList<Facture> DisplayFacture();
 
    List<Facture> DisplayAllCarteFacture();

    public int getFacture();
}
