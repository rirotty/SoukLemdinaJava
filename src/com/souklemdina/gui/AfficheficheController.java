/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.entities.Local;
import com.souklemdina.services.LocalServices;
import com.souklemdina.util.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.souklemdina.util.SessionUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AfficheficheController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private GeocodingService geocodingService;
    private MarkerOptions markerOptions;
    private GoogleMap map;
    protected DirectionsPane directionsPane;
    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();
    GoogleMapView mapView = new GoogleMapView();

    @FXML
    private JFXComboBox<String> type = new JFXComboBox<>();
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField superficie;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXTextField recherche;
    @FXML
    private ImageView imageView;
    @FXML
    private TableView<Local> tableLocal;
    @FXML
    private TableColumn<?, ?> colRef;
    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private TableColumn<?, ?> colSup;
    @FXML
    private TableColumn<?, ?> colPrix;
    @FXML
    private Label err_superficie;
    @FXML
    private Label err_prix;
    @FXML
    private Label err_adresse;
    @FXML
    private Label err_telephone;
    @FXML
    private Label err_description;
    @FXML
    private Label nbr;
    @FXML
    private Label nbr1;
    @FXML
    private Label tel;

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
        mapView.addMapInializedListener(this);

        mapView.addMapInializedListener(this);

        from.bindBidirectional(adresse.textProperty());

        adresse.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    adresse.validate();
                }
            }
        });

        data = FXCollections.observableArrayList();
        setCellTable();
        AfficherLocal();
        setCellToText();
        loadData();
        rechercheLocal();

    }

    public void setCellTable() {
        colRef.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSup.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    public void AfficherLocal() {
        LocalServices ls = new LocalServices();
        //recuperer idUser
        data = ls.afficherLocalUser(SessionUser.getUser().getId());
        tableLocal.setItems(data);
    }

    private void loadData() {
        list.removeAll(list);
        list.addAll("Restaurant", "Café", "Boutique", "Espace vide", "Autres");
        type.getItems().addAll(list);
    }

    @FXML
    public void handleBoutonRetour(ActionEvent event) throws IOException {
        AnchorPane pageAcceuil = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        this.anch_pane.getChildren().setAll(pageAcceuil);
    }

    public void setCellToText() {
        tableLocal.setOnMouseClicked((MouseEvent event) -> {
            LocalServices ls = new LocalServices();
            Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
            Local loc = ls.afficherLocalUn(l.getId());
            type.setValue(l.getType());
            description.setText(l.getDescription());
            superficie.setText(String.valueOf(l.getSuperficie()));
            prix.setText(String.valueOf(l.getPrix()));
            adresse.setText(l.getAdresse());
            telephone.setText(l.getTelephone());
//            afficherImage(l.getId());
            System.out.println("http://localhost/SoukLemdina/web/uploads/images/" + loc.getImage());
            imageView.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + loc.getImage()));
            superficie.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    boolean isNbr1 = TextFieldValidation.isTextFieldNumber(superficie, nbr, "La superfiicie doit etre de type nombre");
                    boolean isSuperficie = TextFieldValidation.isTextFieldNotEmpty(superficie, err_superficie, "Le champ superficie est obligatoire");

                }
            });
            prix.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    boolean isNbr = TextFieldValidation.isTextFieldNumber(prix, nbr1, "Le prix doit etre de type nombre");
                    boolean isPrix = TextFieldValidation.isTextFieldNotEmpty(prix, err_prix, "Le champ prix est obligatoire");

                }
            });
            adresse.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    boolean isAdresse = TextFieldValidation.isTextFieldNotEmpty(adresse, err_adresse, "Le champ adresse est obligatoire");

                }
            });
            description.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    boolean isAdresse = TextFieldValidation.isTextAreaNotEmpty(description, err_description, "Le champ description est obligatoire");

                }
            });

            telephone.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    boolean isTelephone = TextFieldValidation.isTextFieldNotEmpty(telephone, err_telephone, "Le champ telephone est obligatoire");
                    boolean isOui = isNumber(newValue);
                    if (!isOui) {
                        tel.setText("Nméro de téléphone invalide");
                    } else {
                        tel.setText("");
                    }
                }
            });
        });
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
    public boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("(20|21|22|70|71|50|51)[0-9][0-9][0-9][0-9][0-9][0-9]$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void ModifierLocal() {
        boolean isDescription = TextFieldValidation.isTextAreaNotEmpty(description, err_description, "Le champ description est obligatoire");
        boolean isSuperficie = TextFieldValidation.isTextFieldNotEmpty(superficie, err_superficie, "Le champ superficie est obligatoire");
        boolean isPrix = TextFieldValidation.isTextFieldNotEmpty(prix, err_prix, "Le champ prix est obligatoire");
        boolean isTelephone = TextFieldValidation.isTextFieldNotEmpty(telephone, err_telephone, "Le champ telephone est obligatoire");
        boolean isAdresse = TextFieldValidation.isTextFieldNotEmpty(adresse, err_adresse, "Le champ adresse est obligatoire");

        if ((isAdresse) && (isPrix) && (isSuperficie) && (isTelephone) && (isDescription)) {
            LocalServices ls = new LocalServices();
            Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
            l.setIdUser(SessionUser.getUser().getId());
            l.setType(type.getValue());
            l.setDescription(description.getText());
            l.setSuperficie(Double.parseDouble(superficie.getText()));
            l.setPrix(Double.parseDouble(prix.getText()));
            l.setAdresse(adresse.getText());
            l.setTelephone(telephone.getText());
            ls.modifierLocal(l);
            this.tableLocal.refresh();
            AfficherLocal();
        } else {
            AlertBox.display("Alerte", "verifiez donnees");
        }

    }

    @FXML
    public void SupprimerLocal() {
        LocalServices ls = new LocalServices();

        int selectedIndex = tableLocal.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            AlertBox.display("Alerte", "veuillez sélectionner un local ");
        } else {
            Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
//            System.out.println(l.getId());
            ls.supprimerLocal(l.getId());
            this.tableLocal.refresh();
        }
    }

    @FXML
    public void rechercheLocal() {

        recherche.setOnKeyReleased(e -> {

            if (recherche.getText().equals("")) {
                AfficherLocal();
            } else {
                data.clear();
                LocalServices ls = new LocalServices();
                data = ls.rechercheLocal(recherche.getText());
                tableLocal.setItems(data);
            }
        });
    }

    @FXML
    public void handleBoutonAjout(ActionEvent event) throws IOException {
        AnchorPane pageAffiche = FXMLLoader.load(getClass().getResource("Ajouuuut.fxml"));
        this.anch_pane.getChildren().setAll(pageAffiche);
    }

    @FXML
    public void handleBoutonLocation(ActionEvent event) throws IOException {
        AnchorPane pageAffiche = FXMLLoader.load(getClass().getResource("Locations.fxml"));
        this.anch_pane.getChildren().setAll(pageAffiche);
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void fromOnKeyTypedEvent(KeyEvent event) {
        try {
            geocodingService = new GeocodingService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        geocodingService.geocode(adresse.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
            l.clear();
            //int i;                 
            for (int i = 0; i < results.length; i++) {
                s = new String[results.length];
                s[i] = results[i].getFormattedAddress();
                System.out.println(results[i].getJSObject());
                l.add(results[i].getFormattedAddress());

            }

            for (GeocodingResult result : results) {

                TextFields.bindAutoCompletion(adresse, s);
//                longi.setText(result.getGeometry().getLocation().getLatitude() + "");
//                lat.setText(result.getGeometry().getLocation().getLongitude() + "");

            }

            TextFields.bindAutoCompletion(adresse, t -> {

                return l;

            });

        });
    }

}
