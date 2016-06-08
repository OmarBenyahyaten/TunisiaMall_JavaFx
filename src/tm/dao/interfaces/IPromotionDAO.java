/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import tm.entities.Enseigne;
import tm.entities.Promotion;

/**
 *
 * @author omarblythe
 */
public interface IPromotionDAO {
    
     void insertPromotion(Promotion pr);

    void updatePromotion(Promotion pr);

    void deletePromotion(int id);

    ObservableList<Promotion> DisplayAllPromotion();
    
     
     public ObservableList<Promotion> findPromotionByNom(String nom);

    
    
    
    
    
}
