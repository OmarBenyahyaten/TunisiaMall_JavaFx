/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;
import tm.entities.Enseigne;

/**
 *
 * @author MANAI Yosr
 */
public interface IEnseigneDAO {
    boolean insertEnseigne(Enseigne enseigne);

    boolean updateEnseigne(Enseigne enseigne);

    void deleteEnseigne(int id);
    
    ObservableList<Enseigne> findEnseigne(String search) ;
    
    ObservableList<Enseigne> findEnseigneByNom(String nom);
    
    ObservableList<Enseigne>  findEnseigneByEntreprise(String entreprise);
    
    ObservableList<Enseigne>  findEnseigneByHeureOuverture(String ho);
    
    ObservableList<Enseigne>  findEnseigneByHeureFermetture(String hf);
    
    ObservableList<Enseigne>  findEnseigneByStore(String store);

    ObservableList<Enseigne> DisplayAllEnseignes();
    
    List<String> getListEnseigneByID(int id);
    int getIDbyEnseigne(String enseigne);

    public List<String> DisplayCBNom();
    public List<String> DisplayCBEntreprise();
    public List<String> DisplayCBStore();
    public List<String> DisplayCBOuverture();
    public List<String> DisplayCBFermetture();

    public boolean verifStore(String text);
    
    public Enseigne findByNom(String nom);

}
