/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import tm.entities.PackPublicitaire;

/**
 *
 * @author Z500
 */
public interface IPacksPublicitairesDAO {
    

    ObservableList<PackPublicitaire> DisplayAllPacks();
    public PackPublicitaire findPackPublicitaire(int id);
}
