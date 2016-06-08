/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.Map;
import javafx.collections.ObservableList;
import tm.entities.Enseigne;
import tm.entities.ResponsableBoutique;

/**
 *
 * @author MANAI Yosr
 */
public interface IResponsableBoutiqueDAO {
     
    void insertResponsableBoutique(ResponsableBoutique rb);

    void updateResponsableBoutique(ResponsableBoutique rb);

    void deleteResponsableBoutique(int id);

    ObservableList<ResponsableBoutique> DisplayAllResponsableBoutique();
    public ObservableList<ResponsableBoutique> findByEnseigne(int idEnseigne);
     public ObservableList<ResponsableBoutique> findResponsable(String search);
     
     public boolean verifTel(String text);

    public int getIdEnseigne(String toString);

    public ObservableList<ResponsableBoutique> DisplayResponsableBoutique(String id);
    
}
