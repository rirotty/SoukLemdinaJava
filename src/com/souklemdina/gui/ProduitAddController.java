/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.souklemdina.entities.Produit;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProduitAddController implements Initializable {

    private String newPhoto;

    private Label tfuser;
    @FXML
    private TextField tflibelle;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfpromotion;
    private TextField nbsignal;
    @FXML
    private TextField tfprix;


    @FXML
    private ComboBox<String> ctype;

    @FXML
    private ComboBox<String> ccategorie;

    ObservableList<String> tlist;
    ObservableList<String> clist;

    String sctype;
    String sccategorie;
    @FXML
    private Button btn_ajout;
    @FXML
    private TableView<Produit> prod_tbl = new TableView<>();
    @FXML
    private SplitPane main;
    @FXML
    private AnchorPane mainleft;
    @FXML
    private AnchorPane mainright;

    int prodId;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_sup;
    @FXML
    private JFXButton btn_img;
    @FXML
    private Label testLib;
    @FXML
    private Label testdesc;
    @FXML
    private Label testqant;
    @FXML
    private Label testprom;
    @FXML
    private Label testprix;
    @FXML
    private Label testValid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        this.prod_tbl.prefWidthProperty().bind(main.widthProperty().divide(1.5));
//        this.mainleft.prefWidthProperty().bind(main.widthProperty().divide(6));
//        this.mainright.prefWidthProperty().bind(main.widthProperty().divide(2));
        TableColumn<Produit, String> colLibelle = new TableColumn("Libelle");
        TableColumn<Produit, String> colDescription = new TableColumn("Description");
        TableColumn<Produit, Double> colPrix = new TableColumn("Prix");
        TableColumn<Produit, Integer> colPromotion = new TableColumn("Promotion");
        TableColumn<Produit, Image> colPhoto = new TableColumn("Photo");
                TableColumn<Produit, Integer> colQuantite = new TableColumn("Quantité");
        ProduitServices ps = new ProduitServices();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l1.add("En stock");
        l1.add("Sur Commande");
        l2.add("Habillement");
        l2.add("Produits frais");
        l2.add("Décoration");
        l2.add("Arts de la table");
        l2.add("Bijoux");
        l2.add("Maroquinerie");
        tlist = FXCollections.observableList(l1);
        clist = FXCollections.observableList(l2);
        prod_tbl.getColumns().addAll(colPhoto,colLibelle, colDescription, colPrix,colQuantite ,colPromotion);
        colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colPromotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
                colQuantite.setCellValueFactory(new PropertyValueFactory<>("quqntite"));
        colPhoto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, javafx.scene.image.Image>, ObservableValue<javafx.scene.image.Image>>() {

            public ObservableValue<javafx.scene.image.Image> call(TableColumn.CellDataFeatures<Produit, javafx.scene.image.Image> p) {
                System.out.println("file:D:\\Programs\\wamp64\\www\\SoukLemdina\\web\\uploads\\images\\" + p.getValue().getImage());
                return new SimpleObjectProperty<>(new javafx.scene.image.Image("http://localhost/SoukLemdina/web/uploads/images/" + p.getValue().getImage(), 100, 100, true, true, true));
            }
        });
        colPhoto.setCellFactory(new Callback<TableColumn<Produit, javafx.scene.image.Image>, TableCell<Produit, javafx.scene.image.Image>>() {

            public TableCell<Produit, javafx.scene.image.Image> call(TableColumn<Produit, javafx.scene.image.Image> p) {
                return new TableCell<Produit, javafx.scene.image.Image>() {

                    @Override
                    protected void updateItem(javafx.scene.image.Image i, boolean empty) {
                        super.updateItem(i, empty);
                        setText(null);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        ImageView imageView = (i == null || empty) ? null : ImageViewBuilder.create().image(i).build();
                        setGraphic(imageView);
                    }
                };
            }
        });

        prod_tbl.sort();

        prod_tbl.setItems(ps.findByIdUser(SessionUser.getUser().getId()));

        ctype.setItems(tlist);
        ccategorie.setItems(clist);

        prod_tbl.setOnMouseClicked(e -> {
            Produit p = prod_tbl.getSelectionModel().getSelectedItem();
            tflibelle.setText(p.getLibelle());
            tfdescription.setText(p.getDescription());
            tfprix.setText(String.valueOf(p.getPrix()));
            tfpromotion.setText(String.valueOf(p.getPromotion()));
            tfquantite.setText(String.valueOf(p.getQuqntite()));

            prodId = p.getId();
        });

    }

    @FXML
    public void AjoutProduit() {
        Image img = new Image("/ressources/img/logos/app.png", 100, 100, false, false);

        Notifications notificationBuilder = Notifications.create().title("Ajout Produit").text("Votre produit a été créé avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
        ProduitServices ps = new ProduitServices();
        Produit p = new Produit();
        if ((testLib.getText().equals("✔")) && (testdesc.getText().equals("✔")) && (testprix.getText().equals("✔")) && (testprom.getText().equals("✔")) && (testqant.getText().equals("✔"))) {
            p.setIdUser(SessionUser.getUser().getId());
            p.setLibelle(tflibelle.getText());
            p.setDescription(tfdescription.getText());
            p.setQuqntite(Integer.valueOf((tfquantite.getText())));
            p.setPromotion(Integer.valueOf((tfpromotion.getText())));

            sctype = ctype.getSelectionModel().getSelectedItem();
            sccategorie = ccategorie.getSelectionModel().getSelectedItem();
            p.setType(sctype);
            p.setCategorie(sccategorie);
            p.setPrix(Double.valueOf((tfprix.getText())));
            p.setImage(this.newPhoto);
            p.setUpdatedAt(new Timestamp(new Date().getTime()));

            ps.create(p);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            prod_tbl.setItems(ps.findByIdUser(SessionUser.getUser().getId()));
            tfdescription.setText("");
            tflibelle.setText("");
            tfprix.setText("");
            tfpromotion.setText("");
            tfquantite.setText("");

            System.out.println("Ajout effectué avec succès");
        } else {
            testValid.setText("Veuillez vérifier SVP! ");
        }

    }

    @FXML
    private void ModifProduit(ActionEvent event) {
        Produit p = new Produit();
        ProduitServices ps = new ProduitServices();
        p = ps.findById(prodId);
        if ((testLib.getText().equals("✔")) && (testdesc.getText().equals("✔")) && (testprix.getText().equals("✔")) && (testprom.getText().equals("✔")) && (testqant.getText().equals("✔"))) {

            p.setUpdatedAt(new Timestamp(new Date().getTime()));
            p.setLibelle(tflibelle.getText());
            p.setDescription(tfdescription.getText());
            p.setQuqntite(Integer.valueOf((tfquantite.getText())));
            p.setPromotion(Integer.valueOf((tfpromotion.getText())));

            sctype = ctype.getSelectionModel().getSelectedItem();
            sccategorie = ccategorie.getSelectionModel().getSelectedItem();
            p.setType(sctype);
            p.setType(sccategorie);
            p.setPrix(Double.valueOf((tfprix.getText())));
            if (!this.newPhoto.equals(p.getImage())) {
                p.setImage(this.newPhoto);
            }

            ps.update(p);
            tfdescription.setText("");
            tflibelle.setText("");
            tfprix.setText("");
            tfpromotion.setText("");
            tfquantite.setText("");
            System.out.println("Modification effectuée avec succès");
            prod_tbl.getItems().clear();
            prod_tbl.setItems(ps.findByIdUser(SessionUser.getUser().getId()));
        } else {
            testValid.setText("Veuillez vérifier SVP! ");
        }
    }

    @FXML
    private void SuppProduit(ActionEvent event) {
        ProduitServices ps = new ProduitServices();
        Produit p = new Produit();
        p.setId(prodId);
        ps.delete(p);
        prod_tbl.setItems(ps.findByIdUser(SessionUser.getUser().getId()));

    }



    @FXML
    private void selectPhoto(ActionEvent event) throws Exception {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg"));
        file.setTitle("Choisir une photo du produit");
        java.io.File selected_photo = file.showOpenDialog((Stage) this.btn_img.getScene().getWindow());
        this.newPhoto = selected_photo == null ? null : selected_photo.getAbsolutePath();
        System.out.println(this.newPhoto);
        this.newPhoto = UploadFile.uploadImage(this.newPhoto, null);
        System.out.println(this.newPhoto);
    }

    public static boolean validPromotion(int promotion) {
        if ((promotion >= 0) && (promotion < 100)) {
            return true;

        }
        return false;
    }

    @FXML
    private void ValLib(MouseEvent event) {
        if (!tflibelle.getText().matches("[a-z A-Z]+")) {
            testLib.setTextFill(Paint.valueOf("RED"));
            testLib.setText("✘");
        } else {
            testLib.setTextFill(Paint.valueOf("#0000FF"));
            testLib.setText("✔");

        }
    }

    @FXML
    private void ValQuan(MouseEvent event) {
        if (!tfquantite.getText().matches("^[1-9]\\d*$")) {
            testqant.setTextFill(Paint.valueOf("RED"));
            testqant.setText("✘");
        } else {
            testqant.setTextFill(Paint.valueOf("#0000FF"));
            testqant.setText("✔");

        }
    }

    @FXML
    private void ValProm(MouseEvent event) {
        if (tfpromotion.getText().length() > 0) {
            if (!validPromotion(Integer.parseInt(tfpromotion.getText()))) {
                testprom.setTextFill(Paint.valueOf("RED"));
                testprom.setText("✘");
            } else {
                testprom.setTextFill(Paint.valueOf("#0000FF"));
                testprom.setText("✔");

            }
        }
    }

    @FXML
    private void ValPrix(MouseEvent event) {
        if (!tfprix.getText().matches("[+-]?([0-9]*[.])?[0-9]+")) {
            testprix.setTextFill(Paint.valueOf("RED"));
            testprix.setText("✘");
        } else {
            testprix.setTextFill(Paint.valueOf("#0000FF"));
            testprix.setText("✔");

        }
    }

    @FXML
    private void ValDesc(MouseEvent event) {
        if (!tfdescription.getText().matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$")) {
            testdesc.setTextFill(Paint.valueOf("RED"));
            testdesc.setText("✘");
        } else {
            testdesc.setTextFill(Paint.valueOf("#0000FF"));
            testdesc.setText("✔");

        }

    }

}
