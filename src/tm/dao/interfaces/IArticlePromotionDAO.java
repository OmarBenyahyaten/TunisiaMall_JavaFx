/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import tm.entities.ArticlePromotion;

/**
 *
 * @author omarblythe
 */
public interface IArticlePromotionDAO {
      void insertArticlePromotion(ArticlePromotion articlePromotion);

    void updateArticlePromotion(ArticlePromotion articlePromotion);

    void deleteArticlePromotion(int id,int id2);

    ObservableList<ArticlePromotion> DisplayAllArticlePromotion();
    public List<String> DisplayCBArticle();
    public List<String> DisplayCBPromotion();
     public int DisplayCBArticle2(String c);
     public int DisplayCBPromotion2(String c);
     public ArticlePromotion findEnseigneByNom(String nom);
     public List<String> DisplayPromotion();

    public ObservableList<ArticlePromotion> DisplayRechPromotion(int id);
      public int getRemise(int id);
      public int getRemiseGros(int id);

    
}
