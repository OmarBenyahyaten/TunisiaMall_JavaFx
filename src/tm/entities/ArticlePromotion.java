/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;


/**
 *
 * @author omarblythe
 */
public class ArticlePromotion {
    int idArticle;
    int idPromotion;
    float tauxReductionGros;
    float tauxReductionDetail;
    int nombrePointFidele;

    public ArticlePromotion() {
    }

    public ArticlePromotion(int idArticle, int idPromotion, float tauxReductionGros, float tauxReductionDetail, int nombrePointFidele) {
        this.idArticle = idArticle;
        this.idPromotion = idPromotion;
        this.tauxReductionGros = tauxReductionGros;
        this.tauxReductionDetail = tauxReductionDetail;
        this.nombrePointFidele = nombrePointFidele;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public float getTauxReductionGros() {
        return tauxReductionGros;
    }

    public float getTauxReductionDetail() {
        return tauxReductionDetail;
    }

    public int getNombrePointFidele() {
        return nombrePointFidele;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public void setTauxReductionGros(float tauxReductionGros) {
        this.tauxReductionGros = tauxReductionGros;
    }

    public void setTauxReductionDetail(float tauxReductionDetail) {
        this.tauxReductionDetail = tauxReductionDetail;
    }

    public void setNombrePointFidele(int nombrePointFidele) {
        this.nombrePointFidele = nombrePointFidele;
    }

    @Override
    public String toString() {
        return "ArticlePromotion{" + "idArticle=" + idArticle + ", idPromotion=" + idPromotion + ", tauxReductionGros=" + tauxReductionGros + ", tauxReductionDetail=" + tauxReductionDetail + ", nombrePointFidele=" + nombrePointFidele + '}';
    }
    
    
    
}
