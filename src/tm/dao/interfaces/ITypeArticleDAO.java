/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import tm.entities.TypeArticle;

/**
 *
 * @author omarblythe
 */
public interface ITypeArticleDAO {
     void insertTypeArticle(TypeArticle typeArticle);

    void updateTypeArticle(TypeArticle typeArticle);

    void deleteTypeArticle(int id);

    List<TypeArticle> DisplayAllTypeArticle();
        public List<String> DisplayCBCategorie(String genre) ;
         public List<String> DisplayCBTaille(String genre,String categorie);
public int getTypeByGCT(String genre,String categorie,String taille);
     public TypeArticle getTypeByID(int id);
    public List<String> DisplayCBGenre();
}
