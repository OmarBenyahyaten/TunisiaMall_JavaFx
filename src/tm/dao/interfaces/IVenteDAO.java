/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import tm.entities.Vente;

/**
 *
 * @author omarblythe
 */
public interface IVenteDAO {
     void insertVente(ObservableList<Vente> ventes);

    void updateVente(Vente vente);

    void deleteVente(int id);

    List<Vente> DisplayAllVente();
    ObservableList<Vente>DisplayVenteByFacture(int id );
}
