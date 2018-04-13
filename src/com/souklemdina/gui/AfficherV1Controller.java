/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

import com.souklemdina.entities.Local;
import com.souklemdina.entities.Location;
import com.souklemdina.services.LocalServices;
import com.souklemdina.services.LocationServices;
import com.souklemdina.util.DataSource;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AfficherV1Controller implements Initializable {

    @FXML
    private Hyperlink signal;
    @FXML
    private ImageView imageView;
    @FXML
    private TableView<Local> tableLocal;
    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private TableColumn<?, ?> colSuperficie;
    @FXML
    private TableColumn<?, ?> colPrix;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colAdresse;
    @FXML
    private TableColumn<?, ?> colTelephone;
    @FXML
    private TextField recherche;
    @FXML
    private JFXDatePicker dateDebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private Button auth;

    private ObservableList<Local> data;
    private Image image;
    ObservableList list = FXCollections.observableArrayList();
    Connection cnx = DataSource.getInstance().getConnection();
    @FXML
    private AnchorPane anch_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        setCellTable();
        AfficherLocal();
        setCellToText();
        rechercheLocal();

    }

    @FXML
    public void handleSignaler() {
        signal.setDisable(true);
        LocalServices ls = new LocalServices();
        Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
        Local loc = ls.afficherLocalSignaler(l.getId());
        int nb = loc.getNbSignal();
        ls.modifierLocalSignaler(l.getId(), nb + 1);

    }

    public void setCellTable() {
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSuperficie.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    }

    public void AfficherLocal() {
        LocalServices ls = new LocalServices();
        data = ls.afficherLocal();
        tableLocal.setItems(data);
    }

    @FXML
    private void handleBoutonRetour(ActionEvent event) throws IOException {
        AnchorPane pageAcceuil = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        this.anch_pane.getChildren().setAll(pageAcceuil);
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
                imageView.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + loc.getImage()));

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
                data = ls.rechercheLocal(recherche.getText());
                tableLocal.setItems(data);
            }
        });
    }

    public void handleBoutonPartager() {

        Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
        int selectedItem = tableLocal.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0) {
            Local local = new Local();
            local = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
            String accessToken = "EAACEdEose0cBAGMOZBx01Arj8ruUOWoeNoAk19QU0NMmI8j94UZBp0bkkGkqPeR7eSSUrdyw72jrQZBCxGBtDjXHIFAmymYpchDgGmnzuJeqXU93fYX92EYxxrDYOutZBQuyHaWXOBb7PGHNbsO6W7xtB4jfFRwEZA0MJEeZCyamKZAfvMrAd3WXALv83MAXv8TW5xyl5vVX1U2a5ZBN3Mhx8eSZBadbD26IZD";
            LocalServices ls = new LocalServices();
            Local loc = ls.afficherLocalUn(local.getId());
            Scanner s = new Scanner(System.in);
            FacebookClient fbClient = new DefaultFacebookClient(accessToken);
            FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                    Parameter.with("message", "Local " + local.getType() + " " + local.getDescription() + " à " + local.getPrix()),
                    Parameter.with("link", "https://www.google.com/"));
            System.out.println("fb.com/" + response.getId());

            LocalDateTime now = LocalDateTime.now();
            LocalTime lt = now.toLocalTime().plusMinutes(1);
            LocalTime nowT;
            Boolean t = false;
            Notifications notif = Notifications.create()
                    .darkStyle().title("Succés")
                    .text("Partage effectué avec succés")
                    .position(Pos.TOP_LEFT)
                    .hideAfter(Duration.seconds(30))
                    //.graphic(new ImageView(img))
                    .onAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("c bn");
                        }
                    });

            do {
                now = LocalDateTime.now();
                nowT = now.toLocalTime();
                if (nowT.isAfter(lt)) {
                    t = true;
                }

            } while (t == false);
            if (t == true) {
                notif.showInformation();
            }
            System.out.println(accessToken);

        }

    }

    @FXML
    public void handleBoutonAuth() {
        if ((tableLocal.getSelectionModel().getSelectedIndex()) >= 0) {
            String domain = "https://www.google.com/";
            String appId = "1975949365990427";
            String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=email,"
                    + "public_profile";
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

//            ChromeDriverService service = new ChromeDriverService.Builder()
//                    .usingDriverExecutable(new File("/usr/local/bin/chromedriver"))
//                    .usingAnyFreePort()
//                    .build();
//
//            try {
//                service.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            

            WebDriver driver = new ChromeDriver();
            driver.get(authUrl);
//            String accessToken;
            String accessToken = "EAACEdEose0cBAGMOZBx01Arj8ruUOWoeNoAk19QU0NMmI8j94UZBp0bkkGkqPeR7eSSUrdyw72jrQZBCxGBtDjXHIFAmymYpchDgGmnzuJeqXU93fYX92EYxxrDYOutZBQuyHaWXOBb7PGHNbsO6W7xtB4jfFRwEZA0MJEeZCyamKZAfvMrAd3WXALv83MAXv8TW5xyl5vVX1U2a5ZBN3Mhx8eSZBadbD26IZD";

//            while (true) {
//                if (!driver.getCurrentUrl().contains("facebook.com")) {
//                    String url = driver.getCurrentUrl();
//                    accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
//                    driver.quit();
            FacebookClient fbClient = new DefaultFacebookClient(accessToken);
            User user = fbClient.fetchObject("me", User.class);
            System.out.println(user.getName());
            handleBoutonPartager();

//            LocalServices ls = new LocalServices();
//            Local local = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
//            Local loc = ls.afficherLocalUn(local.getId());
//            FacebookType response = fbClient.publish("me/feed", FacebookType.class,
//                    Parameter.with("message", "Local " + local.getType() + local.getDescription() + " à " + local.getPrix()),
//                    Parameter.with("link", new Image("http://localhost/SoukLemdina/web/uploads/images/" + loc.getImage())));
//            System.out.println("fb.com/" + response.getId());
//            Notifications notif = Notifications.create()
//                    .darkStyle().title("Succés")
//                    .text("Partage effectué avec succés")
//                    .position(Pos.TOP_LEFT)
//                    .hideAfter(Duration.seconds(30))
//                    //.graphic(new ImageView(img))
//                    .onAction(new EventHandler<ActionEvent>() {
//
//                        @Override
//                        public void handle(ActionEvent event) {
//                            System.out.println("c bn");
//                        }
//                    });
//       
//                 
//            if (fiveMinutesLater.isEqual(LocalDateTime.now())) {
//                notif.showInformation();
//            }
//        }                        }
//                    }
        } else {
            AlertBox.display("Erreur", "Veuillez sélectionner un local à partager");
        }

    }

    @FXML
    public void louerLocal() throws SQLException {

        boolean b = true;
        if ((dateDebut.getEditor().getText().equals("")) || (dateFin.getEditor().getText().equals("")) || (tableLocal.getSelectionModel().getSelectedIndex() < 0)) {
            AlertBox.display("Erreur", "Veuillez saisir une date de début et une date de fin pour pouvoir effectuer une location et/ou sélectionner un local");
        } else {
            int res = dateFin.getValue().compareTo(dateDebut.getValue());
            LocalDate localDate = LocalDate.now();
            int now = dateDebut.getValue().compareTo(localDate);
            int now1 = dateFin.getValue().compareTo(localDate);
            LocationServices ls = new LocationServices();
            Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
            if (tableLocal.getSelectionModel().getSelectedIndex() < 0) {
                System.out.println("errorrr");
            } else {
                ObservableList<Location> LocationList = ls.afficherLocation(l.getId());
                for (Location Location1 : LocationList) {
                    Date dateD = java.sql.Date.valueOf(dateDebut.getValue());
                    Date dateF = java.sql.Date.valueOf(dateFin.getValue());
                    if (((Location1.getDateDebut().after(dateD)) && (Location1.getDateDebut().before(dateF))) || ((Location1.getDateFin().after(dateD)) && (Location1.getDateFin().before(dateF)))) {
                        b = false;
                    }
                }
                if ((res < 0) || (now < 0) || (now1 < 0)) {
                    AlertBox.display("Erreur", "Données erronés veuillez vérifiez les dates saisies");
                } else {
                    if (b == false) {
                        AlertBox.display("Date non diponible", "Désole ce local n'est pas disponible pour cette date veuillez en choisir une autre merci");
                    } else {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Etes-vous sûr de vouloir reserver ce local pour le " + dateDebut.getEditor().getText() + " jusqu'à " + dateFin.getEditor().getText() + " ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {

//                        String username = System.getProperty("user.email");
//                        String password = System.getProperty("user.password");
//                          int id= l.getIdUser();
//                         String req = "select email from fosUser where id = ?";
//                         PreparedStatement stmt = cnx.prepareStatement(req);
//                         stmt.setInt(1,id);
//                        ResultSet rs = stmt.executeQuery();
//                        try {
//                            while (rs.next()){
//                           String toUser = rs.getString("email");
//                            }
//                        } catch (SQLException ex) {
//                            Logger.getLogger(AfficherV1Controller.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                            //https://myaccount.google.com/lesssecureapps?pli=1
                            String username = "souklemdina39@gmail.com";
                            String password = "souklemdina123456";

                            Properties prop = new Properties();
                            prop.put("mail.smtp.auth", "true");
                            prop.put("mail.smtp.starttls.enable", "true");
                            prop.put("mail.smtp.host", "smtp.gmail.com");
                            prop.put("mail.smtp.port", "587");

                            Session session = Session.getInstance(prop,
                                    new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });

                            try {
                                Message msg = new MimeMessage(session);
                                msg.setFrom(new InternetAddress("malek.touzri@esprit.tn"));
                                msg.setRecipient(Message.RecipientType.TO, new InternetAddress("malek.touzri@esprit.tn"));
                                msg.setSubject("Demande de location du local " + l.getType());
                                msg.setText(" du " + dateDebut.getValue().toString() + " au " + dateFin.getValue().toString());
                                Transport.send(msg);
                                Location location = new Location();
                                Date dateD = java.sql.Date.valueOf(dateDebut.getValue());
                                Date dateF = java.sql.Date.valueOf(dateFin.getValue());
                                location.setIdLocal(l.getId());
                                location.setDateDebut(dateD);
                                location.setDateFin(dateF);
                                location.setIdUser(SessionUser.getUser().getId());
                                //recup idUser
                                ls.ajoutLocation(location);
                                Notifications notif = Notifications.create()
                                        .darkStyle().title("Demande envoyée")
                                        .text("Un mail a été envoyé au proprio vous recevrez bientôt une réponse de sa part")
                                        .position(Pos.TOP_LEFT)
                                        .hideAfter(Duration.seconds(30))
                                        //.graphic(new ImageView(img))
                                        .onAction(new EventHandler<ActionEvent>() {

                                            @Override
                                            public void handle(ActionEvent event) {
                                                System.out.println("c bn");
                                            }
                                        });
                                notif.showInformation();

                            } catch (MessagingException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            alert.hide();
                        }
                    }

                }
            }

        }
    }

}
