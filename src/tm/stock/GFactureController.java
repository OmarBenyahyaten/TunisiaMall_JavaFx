/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stock;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.dialog.Dialogs;
import tm.dao.classes.FactureDAO;
import tm.dao.classes.StockDAO;
import tm.dao.classes.VenteDAO;
import tm.dao.interfaces.IFactureDAO;
import tm.dao.interfaces.IStockDAO;
import tm.dao.interfaces.IVenteDAO;
import tm.entities.ArticlePromotion;
import tm.entities.Facture;
import tm.entities.Vente;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class GFactureController implements Initializable {

    /**
     * Initializes the controller class.
     */
      
     @FXML
    private TableColumn<Vente, Float> tblClmTotalRemise;

    @FXML
    private TableColumn<Vente,Integer> tblClmIDArticle1;

    @FXML
    private TableColumn<Vente, Float> tblClmIDRemise;
    @FXML
    private TableColumn<Vente, String> tblClmNomArticle1;

    @FXML
    private TableColumn<Vente, Integer> tblClmQuantite1;
    @FXML
    private TableColumn<Vente, Float> tblClmTotalTTC;

    @FXML
    private TableColumn<Vente, Float> tblClmTotalTVA;
    @FXML
    private TableView<Vente> tblViewVente;
    @FXML
    private TableColumn<Vente, Float> tblClmTotalHT;
    @FXML
    private TableColumn<Vente, Integer> tblClmPrixUnitaire;

    @FXML
    private TableColumn<Vente, Integer> tblClmTVA1;
    
    
     @FXML
    private TableView<Facture> tblView;

    @FXML
    private TableColumn<Facture, Integer> tblClmIDFacture;
     @FXML
    private TableColumn<Facture, String> tblClmDate;
     @FXML
    private TableColumn<Facture, Float> tblClmTotal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
viewFacture();   
    tblView.setRowFactory( tv -> {
    TableRow<Facture> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        setVente();
    });
    return row ;
});}    
     public void viewFacture()
    {
        IFactureDAO factureDAO = new FactureDAO();
            tblClmIDFacture.setCellValueFactory(new PropertyValueFactory<>("idFacture"));
            tblClmDate.setCellValueFactory(new PropertyValueFactory<>("dateAchat"));
            tblClmTotal.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
            
            tblView.setItems(factureDAO.DisplayFacture());
           // tblView.getColumns().addAll(tblClmIDArticle);


    }
      @FXML
    private void setVente() {
          IVenteDAO ventes = new VenteDAO();
       tblViewVente.setItems(ventes.DisplayVenteByFacture(tblView.getSelectionModel().getSelectedItem().getIdFacture()));

        tblClmIDArticle1.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        tblClmNomArticle1.setCellValueFactory(new PropertyValueFactory<>("nomArticle"));
        tblClmPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        tblClmTVA1.setCellValueFactory(new PropertyValueFactory<>("tva"));
        tblClmQuantite1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tblClmTotalTVA.setCellValueFactory(new PropertyValueFactory<>("totalTVA"));
        tblClmIDRemise.setCellValueFactory(new PropertyValueFactory<>("remise"));
        tblClmTotalRemise.setCellValueFactory(new PropertyValueFactory<>("totalRemise"));
        tblClmTotalHT.setCellValueFactory(new PropertyValueFactory<>("totalHT"));
        tblClmTotalTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));

    }
    
    
         @FXML
    private void btnImprimerOnAction(ActionEvent event) throws DocumentException, FileNotFoundException {
         Document PDFLogReport = new Document();  
         PdfWriter.getInstance(PDFLogReport, new FileOutputStream("facture/facture"+tblView.getSelectionModel().getSelectedItem().getIdFacture()+".pdf"));  
         PDFLogReport.open();        
         PdfPTable LogTable = new PdfPTable(10);  
         PdfPCell table_cell;  
         
                
                 table_cell=new PdfPCell(new Phrase("ID_Article")); 
                 LogTable.addCell(table_cell);  
                 table_cell=new PdfPCell(new Phrase("Designation")); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Prix Unitaire"));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Quantite"));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("TVA (%)"));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Total TVA"));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Remise (%)"));  
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Total Remise")); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Total HT"));  
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Total TTC"));  
                 LogTable.addCell(table_cell);
                 
                 ObservableList<Vente> list = FXCollections.observableArrayList();
                 list = tblViewVente.getItems();
                 
                 for (Vente v : list )
                 {
                 table_cell=new PdfPCell(new Phrase(Integer.toString(v.getIdArticle()))); 
                 LogTable.addCell(table_cell);  
                 table_cell=new PdfPCell(new Phrase(v.getNomArticle())); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(v.getPrixUnitaire())));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Integer.toString(v.getTva())));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Integer.toString(v.getQuantite())));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(v.getTotalTVA())));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(v.getRemise())));  
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(v.getTotalRemise()))); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(v.getTotalHT())));  
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(v.getTotalTTC())));  
                 LogTable.addCell(table_cell);
                 }
                
               table_cell.setBorder(4);
                 table_cell.setColspan(6);

         PDFLogReport.add(LogTable);              
         PDFLogReport.close();   
                 
    }
}
