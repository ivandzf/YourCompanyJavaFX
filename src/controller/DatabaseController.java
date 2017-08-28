package controller;

import db.koneksi;
import function.exit;
import function.mouseDrag;
import function.navigation;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class DatabaseController implements Initializable {
    
    navigation nav = new navigation();
    
    @FXML
    private Label exit;
    
    @FXML
    private TextField server, port, database, username, password;
    
    @FXML
    private void handleExitClicked(){
        exit ex = new exit();
        ex.exitClicked();
    }
    
    @FXML
    public void handleLoginClicked(ActionEvent event){
        try {
            Parent database_parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene database_scene = new Scene(database_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(database_scene);
            app_stage.setTitle("Login");
            mouseDrag md = new mouseDrag();
            md.setDragged(database_parent, app_stage);
            app_stage.getIcons().add(nav.applicationIcon);
            app_stage.show();
                        
        } catch (Exception e) {
            nav.showAlert(Alert.AlertType.ERROR, "Error", null, String.valueOf(e));
        }
    }
    
    @FXML
    private void handleTestKoneksi(ActionEvent event){
        String server_text=server.getText();
        String port_text=port.getText();
        String database_text=database.getText();
        String username_text=username.getText();
        String password_text=password.getText();
        koneksi kon = new koneksi();
        kon.testKoneksi(server_text, port_text, database_text, username_text, password_text);
    }
    
    @FXML
    private void handleSimpanKoneksi(ActionEvent event){
        String server_text=server.getText();
        String port_text=port.getText();
        String database_text=database.getText();
        String username_text=username.getText();
        String password_text=password.getText();
        koneksi kon = new koneksi();
        kon.simpanKoneksi(server_text, port_text, database_text, username_text, password_text);
    }
    
    @FXML
    private void setHover(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color: red;");
    }
        
    @FXML
    private void setDefault(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color:  #4183D7;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("setting.properties"));
            String username_prop = properties.getProperty("user");
            String password_prop = properties.getProperty("password");
            String server_prop = properties.getProperty("serverName");
            String database_prop = properties.getProperty("databaseName");
            String port_prop = properties.getProperty("port");
            server.setText(server_prop);
            port.setText(port_prop);
            database.setText(database_prop);
            username.setText(username_prop);
            password.setText(password_prop);
        }
        catch (IOException e) {
            nav.showAlert(Alert.AlertType.ERROR, "Error", null, "File setting tidak ditemukan..");
        }
        
    }    
    
}
