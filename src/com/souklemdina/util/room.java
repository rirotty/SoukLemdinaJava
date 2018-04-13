package com.souklemdina.util;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.json.simple.parser.ParseException;

public class room implements Initializable {

    public static Thread th;

    static Socket sock;
    static DataOutputStream dos;
    static DataInputStream dis;
    public volatile static boolean running = true;

    @FXML
    public TextField myMsg;
    @FXML
    public TextArea chatLog;

    public void onClickSend() {
        try {
            String msg = myMsg.getText();

            //String json = "{" + " 'name' : '" + data.name + "', 'message' : '" + msg + "'" + "}";
            JSONObject js = new JSONObject();
            js.put("name", data.name);
            js.put("message", msg);

            String json = js.toJSONString();

            System.out.println(json);

            dos.writeUTF(json);
            myMsg.setText("");
            myMsg.requestFocus();

        } catch (IOException E) {
            E.printStackTrace();
        }

    }

    public void buttonPressed(KeyEvent e) {
        if (e.getCode().toString().equals("ENTER")) {
            onClickSend();
        }
    }

    public static void terminate() throws IOException {
        running = false;
        dis.close();
        dos.close();
        sock.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Bonjour Thread initialized");
        running = true;
        try {

            sock = new Socket(data.ip, data.port);
            dos = new DataOutputStream(sock.getOutputStream());
            dis = new DataInputStream(sock.getInputStream());

            dos.writeUTF(data.name);
            /*
            * This Thread let the client recieve the message from the server for any time;
             */
            th = new Thread(() -> {
                try {

                    JSONParser parser = new JSONParser();
                    while (room.running) {
                        String newMsgJson = dis.readUTF();

                        System.out.println("RE : " + newMsgJson);
                        Message newMsg = new Message();

                        Object obj = parser.parse(newMsgJson);
                        JSONObject msg = (JSONObject) obj;

                        newMsg.setName((String) msg.get("name"));
                        newMsg.setMessage((String) msg.get("message"));

                        chatLog.appendText(newMsg.getName() + " : " + newMsg.getMessage() + "\n");
                    }
                } catch (IOException | ParseException e) {
                    System.out.println(e.getMessage());
                }
            });

            th.start();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
