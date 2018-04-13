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
import com.souklemdina.services.ParticipantWorkService;
import com.souklemdina.services.WorkshopServices;
import com.souklemdina.util.DataSource;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StatistiquesWorkController implements Initializable {

    @FXML
    private PieChart pie2;
    WorkshopServices ws = new WorkshopServices();
    ParticipantWorkService pws = new ParticipantWorkService();
    @FXML
    private PieChart pie3;
    @FXML
    private ProgressBar progress1;
    @FXML
    private ProgressBar progress2;
    @FXML
    private ProgressBar progress3;
    @FXML
    private Label tot1;
    @FXML
    private Label tot2;
    @FXML
    private Label tot3;
    @FXML
    private Label pourcentage;
    @FXML
    private Label pour1;
    @FXML
    private Label pour2;
    @FXML
    private Button btn;
    @FXML
    private Pane pp;
    @FXML
    private Label pr1;
    @FXML
    private Label pr2;
    @FXML
    private Label pr3;
    @FXML
    private AnchorPane anxc;
    @FXML
    private Button listePart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Userss();
        loadchart();
        loadchart2();
        loadchart3();
        loadchar();
    }

    public StatistiquesWorkController() {
        Connection conn = DataSource.getInstance().getConnection();
    }

    private void loadchart() {

        Nadia();

    }

    private void loadchart2() {

        Prog1();

    }

    private void loadchart3() {

        Prog2();

    }

    private void loadchar() {

        Prog3();

    }

    public void Userss() {

        // new FadeInUpTransition(commBar).play();
        //new FadeInDowntransition(UserComm).play();
        pie2.getData().clear();
        pie2.setData(pws.UsersActif());
        pie2.setAnimated(true);
        pie2.setVisible(true);
        pie2.setTitle(" les Actifs");

    }

    public void Nadia() {

        // new FadeInUpTransition(commBar).play();
        //new FadeInDowntransition(UserComm).play();
        pie3.getData().clear();
        System.out.println("++++++++++++" + pws.participParWork());
        pie3.setData(pws.participParWork());
        pie3.setAnimated(true);
        pie3.setVisible(true);
        pie3.setTitle("Participant par Workshop");

        System.out.println(pie3);
    }

    public void Prog1() {
        double x = ws.nbType1();
        double m = ws.nbWorks();
        tot1.setText("Nombre Total : " + ((Double) m).toString());
        double prc = (x / m) * 100;
        float k = (float) Math.round(prc * 100) / 100;
        pourcentage.setText((String.valueOf(k)) + "% Patisserie");
        float j = k / 100;
        progress1.setProgress(j);
        pr1.setText("% au total");
        System.out.println("pourcentage ====>" + j);

    }

    public void Prog2() {
        double x = ws.nbType2();
        double m = ws.nbWorks();
        tot2.setText("Nombre Total : " + ((Double) m).toString());
        double prc1 = (x / m) * 100;
        float k = (float) Math.round(prc1 * 100) / 100;
        pour1.setText((String.valueOf(k)) + "% Haute couture");
        //progress2.setProgress(k / 100);
        float j = k / 100;
        progress2.setProgress(j);
        pr2.setText("% au total");
        System.out.println("wwwww");

    }

    public void Prog3() {
        double x = ws.nbType3();
        double m = ws.nbWorks();
        tot3.setText("Nombre Total : " + ((Double) m).toString());
        double prc2 = (x / m) * 100;
        float k = (float) Math.round(prc2 * 100) / 100;
        pour2.setText((String.valueOf(k)) + "% autre");
        float j = k / 100;
        progress3.setProgress(j);
        pr3.setText("% au total");
        System.out.println("wezzz");

    }

    @FXML
    private void onload(ActionEvent event) throws IOException {
        try {
            WritableImage image = pie2.snapshot(new SnapshotParameters(), null);
            WritableImage image1 = pie3.snapshot(new SnapshotParameters(), null);
            WritableImage image2 = pp.snapshot(new SnapshotParameters(), null);

            File file = new File("chart.png");
            File file1 = new File("chart1.png");
            File file2 = new File("chart2.png");

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            ImageIO.write(SwingFXUtils.fromFXImage(image1, null), "png", file1);
            ImageIO.write(SwingFXUtils.fromFXImage(image2, null), "png", file2);

            OutputStream filex = new FileOutputStream(new File(System.getProperty("user.home") + "/Desktop/Test.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, filex);

            document.open();

            Image img = Image.getInstance("chart.png");
            Image img1 = Image.getInstance("chart1.png");
            Image img2 = Image.getInstance("chart2.png");

            document.add(new Paragraph(new Date().toString()));

            document.add(img);
            document.add(img1);
            document.add(img2);

            document.close();
            filex.close();
            System.out.println(System.getProperty("user.home") + "/Desktop/Test.pdf");
            Process pr = Runtime.getRuntime().exec("xdg-open " + System.getProperty("user.home") + "/Desktop/Test.pdf");
//            p = pb.start();     // Start the process.
            pr.waitFor();                // Wait for the process to finish.
//            Desktop.getDesktop().open(new File(System.getProperty("user.home") + "/Desktop/Test.pdf"));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    void goTo(ActionEvent e) throws IOException {

        ScrollPane p = FXMLLoader.load(getClass().getResource("AdminAff.fxml"));
        this.anxc.getChildren().setAll(p);

    }
}
