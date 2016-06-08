/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;
import tm.entities.ClientFideleDesktop;

/**
 *
 * @author omarblythe
 */
public interface IClientFideleDesktopDAO {
     void insertClientFideleDesktop(ClientFideleDesktop clientFideleDesktop);

    void updateClientFideleDesktop(ClientFideleDesktop clientFideleDesktop);

    void deleteClientFideleDesktop(int id);

    ObservableList<ClientFideleDesktop> DisplayAllClientFideleDesktop();
}
