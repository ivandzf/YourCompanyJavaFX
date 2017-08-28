package controller;

import function.mouseDrag;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.homeModel;
import model.userModel;

public class UserController implements Initializable {

    userModel model = new userModel();
    navigation nav = new navigation();
    
    @FXML
    private Label idLabel;
    
    @FXML
    private TextField usernameField, namaField, emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void simpanClicked(ActionEvent event) throws IOException{
        model.updateUser(idLabel.getText(), usernameField.getText(), passwordField.getText(), namaField.getText(), emailField.getText());
        if(model.getStatus()==true){
            homeModel modelStatus = new homeModel();
            modelStatus.setStatus(idLabel.getText());
            nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "Profil berhasil tersimpan..");
            Parent database_parent = FXMLLoader.load(getClass().getResource(nav.getLogin()));
            Scene database_scene = new Scene(database_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(database_scene);
            app_stage.setTitle("Login");
            mouseDrag md = new mouseDrag();
            md.setDragged(database_parent, app_stage);
            app_stage.show();
        }
        else{
            nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Profil gagal tersimpan..");
        }
    }
    
    public void setValue(String id, String username, String password, String nama, String email){
        idLabel.setText(id);
        usernameField.setText(username);
        passwordField.setText(password);
        namaField.setText(nama);
        emailField.setText(email);
    }
    
}
