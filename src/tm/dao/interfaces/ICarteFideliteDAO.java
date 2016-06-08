/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import tm.entities.CarteFidelite;

/**
 *
 * @author omarblythe
 */
public interface ICarteFideliteDAO {
     void insertCarteFidelite(CarteFidelite cartefidelite);

    void updateCarteFidelite(CarteFidelite cartefidelite);

    void deleteCarteFidelite(int id);
      public List<String> Displaylistclientf();
      public List<String> Displaylistenseigne();


    

    

    public ObservableList<CarteFidelite> DisplayAllCarteClientFidele();
}
