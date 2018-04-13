/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXTextField;
import com.souklemdina.entities.Local;
import com.souklemdina.services.LocalServices;
import com.souklemdina.util.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class BackLocalController implements Initializable {

    @FXML
    private TableView<Local> tableLocal;
    @FXML
    private TableColumn<Local, String> colType;
    @FXML
    private TableColumn<Local, String> colAdresse;
    @FXML
    private TableColumn<Local, String> colTelephone;
    @FXML
    private TableColumn<Local, Integer> colSignal;
      @FXML
    private TableColumn<Local, Integer> colId;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextField recherche;

    private ObservableList<Local> data;
    private Image image;
    ObservableList list = FXCollections.observableArrayList();
    Connection cnx = DataSource.getInstance().getConnection();
    @FXML
    private AnchorPane anch_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        
        setCellTable();
        AfficherLocal();
        setCellToText();
        rechercheLocal();
        
    }

    public void setCellTable() {
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colSignal.setCellValueFactory(new PropertyValueFactory<>("nbSignal"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

    }

    private void handleBoutonRetour(ActionEvent event) throws IOException {
        AnchorPane pageAcceuil = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));//retour acceuil lshih
        this.anch_pane.getChildren().setAll(pageAcceuil);
    }

    public void AfficherLocal() {
        
        LocalServices ls = new LocalServices();
        data = ls.afficherLocal2();
        tableLocal.setItems(data);
        
    }

//    public void afficherImage(int id) {
//        try {
//            String req = "select located from local where id = ?";
//            PreparedStatement stmt = cnx.prepareStatement(req);
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                InputStream is = rs.getBinaryStream("located");
//                OutputStream os = new FileOutputStream(new File("photo.jpg"));
//                byte[] contents = new byte[1024];
//                int size = 0;
//                while ((size = is.read(contents)) != -1) {
//                    os.write(contents, 0, size);
//                }
//                image = new Image("file:photo.jpg", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
//                imageView.setImage(image);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(LocalServices.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(LocalServices.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(LocalServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void setCellToText() {
        tableLocal.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LocalServices ls = new LocalServices();
                Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
//                afficherImage(l.getId());
                 Local loc = ls.afficherLocalUn(l.getId());
                imageView.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/"+loc.getImage()));

            }
        });
    }

    public void rechercheLocal() {
        recherche.setOnKeyReleased(e -> {

            if (recherche.getText().equals("")) {
                AfficherLocal();
            } else {
                data.clear();
                LocalServices ls = new LocalServices();
                data = ls.rechercheBack(recherche.getText());
                tableLocal.setItems(data);
            }
        });
    }

    @FXML
    public void handleBoutonSupprimer() {
        LocalServices ls = new LocalServices();

        int selectedIndex = tableLocal.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            AlertBox.display("Alerte", "veuillez sélectionner un local ");
        } else {
            Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
            ls.supprimerLocal(l.getId());
        }
    }

    @FXML
    public void repererSignal() {
        LocalServices ls = new LocalServices();
        ObservableList<Local> locaux = ls.afficherLocal2();
        for (Local local : locaux){
        if (local.getNbSignal()>10){
                    Notifications notif = Notifications.create()
                                        .darkStyle().title("Alerte")
                                        .text("Le local "+local.getId()+" a atteint la limite du nombre de signalement!")
                                        .position(Pos.TOP_LEFT)
                                        .hideAfter(Duration.seconds(30))
                                        //.graphic(new ImageView(img))
                                        .onAction(new EventHandler<ActionEvent>() {

                                            @Override
                                            public void handle(ActionEvent event) {
                                                System.out.println("c bn");
                                            }
                                        });
                                notif.showWarning();
        }
        }

    }
}
