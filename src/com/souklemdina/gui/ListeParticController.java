/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantWork;
import com.souklemdina.entities.Workshop;
import com.souklemdina.services.FosUserServices;
import com.souklemdina.services.ParticipantWorkService;
import com.souklemdina.services.WorkshopServices;
import com.souklemdina.util.SessionUser;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ListeParticController implements Initializable {

    @FXML
    private TableView<FosUser> lista;
    Workshop w = new Workshop();
    ObservableList<FosUser> items = FXCollections.observableArrayList();

    FosUserServices us = new FosUserServices();
    FosUser u = SessionUser.getUser();
    List<FosUser> lstU = new ArrayList();
    WorkshopServices ws = new WorkshopServices();
    ArrayList<ParticipantWork> ps = new ArrayList<>();
    ParticipantWorkService wps = new ParticipantWorkService();
    @FXML
    private Label idlab;
    @FXML
    private AnchorPane anc;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        w = ws.findbyid(u.getId());
        ps = wps.getWorkshopById(w.getId());//nediw lel lista mta3 les participan fl work adheka
        idlab.setText("Liste des participants du workshop  " + w.getNomWorkshop());

        for (int i = 0; i < ps.size(); i++) {

            FosUser u1 = new FosUser();
            u1 = ws.getUserById(ps.get(i).getIdUser());
            System.out.println("fn" + u1.getUsername());
            lstU.add(u1);

        }
        TableColumn<FosUser, String> nom = new TableColumn<>("Nom");
        nom.setMinWidth(20);
        nom.setCellValueFactory(new PropertyValueFactory<>("username"));
        System.out.println(" 98888" + nom);
        TableColumn<FosUser, String> tel = new TableColumn<>("Num de tel");
        tel.setMinWidth(20);
        tel.setCellValueFactory(new PropertyValueFactory<>("phone"));
        System.out.println(" 98888" + tel);
        TableColumn<FosUser, String> mail = new TableColumn<>("email");
        mail.setMinWidth(20);
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));

        lista.setItems(items);
        lista.getColumns().addAll(nom, tel, mail);
        for (int i = 0; i < lstU.size(); i++) {

            items.add(lstU.get(i));
            //System.out.println(tableCommande.getSelectionModel().selectedItemProperty());
            //System.out.println();
        }

    }

    @FXML
    void goTolist(ActionEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("ListeWork.fxml"));
        this.anc.getChildren().setAll(p);

    }

    @FXML
    private void onload(ActionEvent event) throws IOException {
        try {
            //WritableImage image = idlab.snapshot(new SnapshotParameters(), null);
            WritableImage image = anc.snapshot(new SnapshotParameters(), null);

//            WritableImage image3 = progress2.snapshot(new SnapshotParameters(), null);
//            WritableImage image4 = progress3.snapshot(new SnapshotParameters(), null);
// TODO: probably use a file chooser here
            File file = new File("Participants.png");
            //File file1 = new File("chart1.png");

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            //ImageIO.write(SwingFXUtils.fromFXImage(image1, null), "png", file1);

            OutputStream filex = new FileOutputStream(new File(System.getProperty("user.home") + "/Desktop/Participants.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, filex);

            document.open();

            Image img = Image.getInstance("Participants.png");
            //Image img1 = Image.getInstance("chart1.png");

//            Image img3 = Image.getInstance("chart3.png");
//            Image img4 = Image.getInstance("chart4.png");
            document.add(img);
            //document.add(img1);

            document.add(new Paragraph(new Date().toString()));

            document.close();
            filex.close();
            Desktop.getDesktop().open(new File(System.getProperty("user.home") + "/Desktop/Participants.pdf"));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
