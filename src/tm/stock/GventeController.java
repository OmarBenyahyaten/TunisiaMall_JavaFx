/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stock;
 import com.itextpdf.text.Document;  
 import com.itextpdf.text.Phrase;  
 import com.itextpdf.text.pdf.PdfPCell;  
 import com.itextpdf.text.pdf.PdfPTable;  
 import com.itextpdf.text.pdf.PdfWriter;  
import com.github.sarxos.webcam.Webcam;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import tm.dao.classes.ArticlePromotionDAO;
import tm.dao.classes.FactureDAO;
import tm.dao.classes.StockDAO;
import tm.dao.classes.VenteDAO;
import tm.dao.interfaces.IArticlePromotionDAO;
import tm.dao.interfaces.IFactureDAO;
import tm.dao.interfaces.IStockDAO;
import tm.dao.interfaces.IVenteDAO;
import tm.entities.Facture;
import tm.entities.Stock;
import tm.entities.Vente;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class GventeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Button start;
	
	@FXML private ImageView imageView;
                 
@FXML Label totalHT ;
@FXML Label totalTTC ;
@FXML Label totalTVA;
    
    
    @FXML
    private TableView<Stock> tblView;
     @FXML
    private TableColumn<Stock,String> tblClmIDArticle;
      @FXML
    private TableColumn<Stock,Integer> tblClmQuantite;
      @FXML
    private TableColumn<Stock, Integer> tblClmIDEnseigne;
    @FXML
    private TableColumn<Stock, String> tblClmNomArticle;
    @FXML
    private TableColumn<Stock,Float> tblClmPrixAchat;
    @FXML
    private TableColumn<Stock, Integer> tblClmTVA;
    @FXML
    private TableColumn<Stock, Float> tblClmPrixVenteGros;
    @FXML
    private TableColumn<Stock, Float> tblClmPrixVenteDetail;
     @FXML
    private TableColumn tblClmAction;
     @FXML
     private TableColumn tblClmActionVente;
     
     
                     ObservableList<Vente> ventes = FXCollections.observableArrayList();

     
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewStock();
         tblClmQuantite1.setCellValueFactory(new Callback<CellDataFeatures<Vente, Integer>, ObservableValue<Integer>>() {
        public ObservableValue<Integer> call(CellDataFeatures<Vente, Integer> p) {
            return new SimpleObjectProperty(p.getValue().getQuantite());
        }
    });
       
        tblClmQuantite1.setEditable(true);

        tblClmQuantite1.setCellFactory(TextFieldTableCell.<Vente, Integer>forTableColumn(new IntegerStringConverter()));
tblClmQuantite1.setOnEditCommit(
    new EventHandler<CellEditEvent<Vente, Integer>>() {
        @Override
        public void handle(CellEditEvent<Vente, Integer> t) {
            System.out.println("aaaaaa");
            Vente v = new Vente() ;
           
        v.setIdArticle(tblViewVente.getSelectionModel().getSelectedItem().getIdArticle());
            System.out.println(v.getIdArticle());
                v=ventes.get(ventes.indexOf(v));
            v.setQuantite(t.getNewValue());
                            System.out.println(v.getIdArticle());
                            calculVente(v);
            viewVente();
        }

    }
);
        
    }
    
     @FXML
    private void StartOnAction(ActionEvent event) {
                         WebCamImageProvider imageProvider = new WebCamImageProvider();
				imageView.imageProperty().bind(imageProvider.imageProperty());
				
				imageProvider.setOnSucceeded(event1 -> {Vente v = new Vente();v.setIdArticle(Integer.parseInt(imageProvider.getQrCode()));addToVenteCam(v);StartOnAction(event);});
				
				Thread t = new Thread(imageProvider);
				t.setDaemon(true);
				t.start();
    }
    
     @FXML
    private void EditOnAction(Event event) {
               // tblClmQuantite1.setCellFactory(TextFieldTableCell.<Vente, Integer>forTableColumn(new IntegerStringConverter()));

    tblClmQuantite1.setOnEditCommit(
    new EventHandler<CellEditEvent<Vente, Integer>>() {
        @Override
        public void handle(CellEditEvent<Vente, Integer> t) {
            Vente v = new Vente() ;
        v.setIdArticle(tblViewVente.getSelectionModel().getSelectedItem().getIdArticle());
                                    System.out.println(v.getIdArticle());

                v=ventes.get(ventes.indexOf(v));
                            System.out.println(v.getIdArticle());

            v.setQuantite(t.getNewValue());
            calculVente(v);
           // System.out.println(v.getQuantite());
            viewVente();
        }

    }
);
    }
    public void calculLabelPrix()
    {
        float ht=0,ttc=0,tva=0 ;
    for (Vente v : ventes)
    {
    ht+=v.getTotalHT();
    ttc+=v.getTotalTTC();
    tva+=v.getTotalTVA();
    }
        System.out.println("aaaaaaaaaaaaaaaaaaaaa"+ht);
    totalHT.setText("Total HT : "+ht);
    totalTVA.setText("Total TVA : "+tva);
    totalTTC.setText("Prix a payer : "+ttc);
    }
    public void calculVente(Vente v){
     if (v.getQuantite()<20){
     v.setPrixUnitaire(tblView.getSelectionModel().getSelectedItem().getPrixVenteDetail());

        float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemise(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        } else {
            v.setPrixUnitaire(tblView.getSelectionModel().getSelectedItem().getPrixVenteGros());
          float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemiseGros(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        }
    }
    public void addToVenteCam(Vente v)
    {
        Stock s = new Stock();
        s.setIdArticle(v.getIdArticle());
        tblView.getSelectionModel().select(s);
if (!ventes.contains(v)){
        v.setNomArticle(tblView.getSelectionModel().getSelectedItem().getNomArticle());
        v.setPrixUnitaire(tblView.getSelectionModel().getSelectedItem().getPrixVenteDetail());
        v.setTva(tblView.getSelectionModel().getSelectedItem().getTva());
        v.setQuantite(1);
        //Calcul total TVA
        float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemise(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        
        
        ventes.add(v);}
else {
        v= ventes.get(ventes.indexOf(v));
        v.setQuantite(v.getQuantite()+1);
        if (v.getQuantite()<20){
        float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemise(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        } else {
            v.setPrixUnitaire(tblView.getSelectionModel().getSelectedItem().getPrixVenteGros());
          float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemiseGros(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        }
        
}
       viewVente();calculLabelPrix();
    }
    public void addToVente(){
Vente v = new Vente();
        v.setIdArticle(tblView.getSelectionModel().getSelectedItem().getIdArticle());
if (!ventes.contains(v)){
        v.setNomArticle(tblView.getSelectionModel().getSelectedItem().getNomArticle());
        v.setPrixUnitaire(tblView.getSelectionModel().getSelectedItem().getPrixVenteDetail());
        v.setTva(tblView.getSelectionModel().getSelectedItem().getTva());
        v.setQuantite(1);
        //Calcul total TVA
        float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemise(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        
        
        ventes.add(v);}
else {
        v= ventes.get(ventes.indexOf(v));
        v.setQuantite(v.getQuantite()+1);
        if (v.getQuantite()<20){
        float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemise(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        } else {
            v.setPrixUnitaire(tblView.getSelectionModel().getSelectedItem().getPrixVenteGros());
          float totalTVA ;
        totalTVA = ((v.getPrixUnitaire()*v.getTva())/100)*v.getQuantite();
        v.setTotalTVA(totalTVA);
        // Get pourcentage du promotion de l'article
        IArticlePromotionDAO ap = new ArticlePromotionDAO();
        v.setRemise(ap.getRemiseGros(v.getIdArticle()));
        //calcule Total Remise
        float totalRemise;
        totalRemise = ((v.getPrixUnitaire()*v.getRemise())/100)*v.getQuantite();
        v.setTotalRemise(totalRemise);
        //Calcul du Total hors taxe
        float totalHT;
        totalHT = (v.getPrixUnitaire()*v.getQuantite()) - totalTVA - totalRemise ;
        v.setTotalHT(totalHT);
        //Calcul du Total tt taxe compris
        float totalTTC ;
        totalTTC=(v.getPrixUnitaire()*v.getQuantite()) + totalTVA - totalRemise ;
        v.setTotalTTC(totalTTC);
        }
        
}
       viewVente();calculLabelPrix();
    }
    public void viewVente(){
                tblViewVente.setItems(ventes);

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
          tblClmActionVente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        tblClmActionVente.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonSupp(tblViewVente);
            }
        });
        //tblViewVente.getSelectionModel().selectFirst();
    }
    
    public void viewStock()
    {
        IStockDAO stockDAO = new StockDAO();
            tblClmIDArticle.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
            tblClmNomArticle.setCellValueFactory(new PropertyValueFactory<>("nomArticle"));
            tblClmIDEnseigne.setCellValueFactory(new PropertyValueFactory<>("Enseigne"));
            tblClmPrixAchat.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
            tblClmPrixVenteDetail.setCellValueFactory(new PropertyValueFactory<>("prixVenteDetail"));
            tblClmPrixVenteGros.setCellValueFactory(new PropertyValueFactory<>("prixVenteGros"));
            tblClmQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tblClmTVA.setCellValueFactory(new PropertyValueFactory<>("tva"));
            
            
                 tblClmAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        tblClmAction.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(tblView);
            }
        });
            
            
            tblView.setItems(stockDAO.DisplayStocks(""));
           // tblView.getColumns().addAll(tblClmIDArticle);
                    tblView.getSelectionModel().selectFirst();


    }
    
    @FXML
    private void btnValiderOnAction(ActionEvent event) throws DocumentException, FileNotFoundException {
        Facture f= new Facture();
        float prixTotal=0 ;
        for (Vente v : ventes){
        prixTotal+= v.getTotalTTC();
        }
        f.setPrixTotal(prixTotal);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
System.out.println(df.format(dateobj));
        f.setDateAchat(df.format(dateobj).toString());
        IFactureDAO facture = new FactureDAO();
        facture.insertFacture(f);
        
         Document PDFLogReport = new Document();  
         PdfWriter.getInstance(PDFLogReport, new FileOutputStream("facture/facture"+facture.getFacture()+".pdf"));  
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

        
        
        for (Vente v : ventes){
            
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


v.setIdFacture(facture.getFacture());
        }
                 table_cell.setBorder(4);
                 table_cell.setColspan(6);

         PDFLogReport.add(LogTable);              
         PDFLogReport.close();  
        IVenteDAO v = new VenteDAO();
        v.insertVente(ventes);
    
    }
    
    
     private class ButtonCell extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonEdit = new Hyperlink("Ajouter");
        final HBox hb = new HBox(cellButtonEdit);
        ButtonCell(final TableView tblView1){
            hb.setSpacing(10);
            
            //cell delete
           
            //cell edit
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                 int row = getTableRow().getIndex();
            tblView.getSelectionModel().select(row);
               addToVente();



            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
    }
     
       private class ButtonSupp extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonSupp = new Hyperlink("Supprimer");
        final HBox hbb = new HBox(cellButtonSupp);
        ButtonSupp(final TableView tblView1){
            hbb.setSpacing(10);
            
            //cell delete
           
            //cell edit
            cellButtonSupp.setOnAction((ActionEvent event) -> {
            int row = getTableRow().getIndex();
            tblViewVente.getSelectionModel().select(row);

           ventes.remove(tblViewVente.getSelectionModel().getSelectedItem());
viewVente();

            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hbb);
            }else{
                setGraphic(null);
            }
        }
    }
    
}
