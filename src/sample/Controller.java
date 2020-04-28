package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public WebView viewMes;
    public TextArea textSend;
    private WebEngine engine;
    private URL url;

    {
        try {
            url = new File("src/sample/chat.html").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        if (textSend.getText().isEmpty()) return;
        JSObject jsObject = (JSObject) engine.executeScript("window");
        jsObject.call("mesBoobl", "myBoobl", "idMyBoobl", textSend.getText());
        textSend.clear();

    }


    public void sendText(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (textSend.getText().isEmpty()) return;
            JSObject jsObject = (JSObject) engine.executeScript("window");
            jsObject.call("mesBoobl", "youBoobl", "idYouBoobl",textSend.getText());
            textSend.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = viewMes.getEngine();
        engine.load(url.toExternalForm());
    }
}
