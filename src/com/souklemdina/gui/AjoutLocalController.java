/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

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
import com.souklemdina.entities.Local;
import com.souklemdina.services.LocalServices;
import com.souklemdina.util.SessionUser;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AjoutLocalController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private GeocodingService geocodingService;
    private MarkerOptions markerOptions;
    private GoogleMap map;
    protected DirectionsPane directionsPane;
    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();
    GoogleMapView mapView = new GoogleMapView();

//    @FXML
//    private TextField longi;
//    @FXML
//    private TextField lat;

    ObservableList list = FXCollections.observableArrayList();
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

    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private File monImage;
    public final Desktop desktop = Desktop.getDesktop();

    @FXML
    private Label err_superficie;
    @FXML
    private Label err_prix;
    @FXML
    private Label err_adresse;
    @FXML
    private Label err_telephone;
    @FXML
    private Label nbr;
    @FXML
    private Label nbr1;
    @FXML
    private Label tel;
    @FXML
    private Label err_description;

    @FXML
    private ImageView imageView;
    private Image image;

    private FileInputStream fis;
    @FXML
    private AnchorPane anch_pane;

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
        loadData();
        type.setValue("Restaurant");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "* *"),
                new FileChooser.ExtensionFilter("images", "*png", "*jpg", "*gif")
        );

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
                boolean isDescription = TextFieldValidation.isTextAreaNotEmpty(description, err_description, "Le champ description est obligatoire");

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
    }

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
    private void handleBoutonRetour(ActionEvent event) throws IOException {
        ScrollPane pageAffiche = FXMLLoader.load(getClass().getResource("Afficher1.fxml"));
        this.anch_pane.getChildren().setAll(pageAffiche);
    }

    public void handleClose() {
        System.exit(0);
    }

    @FXML
    public void AjoutLocal(ActionEvent event) {

        boolean isDescription = TextFieldValidation.isTextAreaNotEmpty(description, err_description, "Le champ description est obligatoire");
        boolean isSuperficie = TextFieldValidation.isTextFieldNotEmpty(superficie, err_superficie, "Le champ superficie est obligatoire");
        boolean isAdresse = TextFieldValidation.isTextFieldNotEmpty(adresse, err_adresse, "Le champ adresse est obligatoire");
        boolean isPrix = TextFieldValidation.isTextFieldNotEmpty(prix, err_prix, "Le champ prix est obligatoire");
        boolean isTelephone = TextFieldValidation.isTextFieldNotEmpty(telephone, err_telephone, "Le champ telephone est obligatoire");

        LocalServices ls = new LocalServices();
        Local l = new Local();
        if ((!isAdresse) || (!isPrix) || (!isSuperficie) || (!isTelephone) || (!isDescription)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Ceci est un message d'alerte");
            alert.setContentText("Veuillez vérifier les données saisies et/ou remplir les champs obligatoires");
            alert.show();
        } else {
            try {
                fis = new FileInputStream(monImage);
                //recuperer idUser et l'inserer
                l.setType(type.getValue());
                l.setIdUser(SessionUser.getUser().getId());
                l.setDescription(description.getText());
                l.setSuperficie(Double.parseDouble(superficie.getText()));
                l.setPrix(Double.parseDouble(prix.getText()));
                l.setAdresse(adresse.getText());
                l.setTelephone(telephone.getText());
//                Double longitude = Double.parseDouble(longi.getText());
//                Double latitude = Double.parseDouble(lat.getText());

                ls.ajoutLocal(l, fis, monImage);
                Parent homePage;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajouuuut.fxml"));
                    homePage = loader.load();

                    Scene homePage_scene = new Scene(homePage);

                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.setScene(homePage_scene);

                    app_stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(AjoutLocalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                description.clear();
                adresse.clear();
                telephone.clear();
                superficie.clear();
                prix.clear();
//                System.out.println("Local ajouté");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Succés");
//                alert.setContentText("Local ajouté!");
//                alert.showAndWait().ifPresent(rs -> {
//                    if (rs == ButtonType.OK) {
//                        System.out.println("bien");
//                    }
//                });
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadData() {
        list.removeAll(list);
        list.addAll("Restaurant", "Café", "Boutique", "Espace vide", "Autres");
        type.getItems().addAll(list);
    }

    @FXML
    private void handleBrowser(ActionEvent event) {
        stage = (Stage) this.anch_pane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        /* try {
         desktop.open(file);
         } catch (IOException ex) {
         Logger.getLogger(AjoutLocalController.class.getName()).log(Level.SEVERE, null, ex);
         }*/

        if (file != null) {
            System.out.println("" + file.getAbsolutePath());
            monImage = new File(file.getAbsolutePath());
            image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);

        }
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
