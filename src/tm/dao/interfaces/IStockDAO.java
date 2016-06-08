/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import tm.entities.Stock;
import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;

/**
 *
 * @author mahdi
 */
public interface IStockDAO {

   boolean insertStock(Stock stock);

    boolean updateStock(Stock stock);

    void deleteSotck(int id);
    public ObservableList<Stock> findStock(String search) ;


    Stock findStockByNum(int num);

    ObservableList<Stock> DisplayStocks(String search);

    public String getStock();
}
