package controller;

import function.navigation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.userManagementModel;

public class TambahUserManagementController implements Initializable {

    navigation nav = new navigation();
    userManagementModel model = new userManagementModel();
    ObservableList<String> comboPilihan = FXCollections.observableArrayList("Operator","User");
    
    @FXML
    private TextField username, nama, email;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private ComboBox level;
    
    private void clear(){
        username.setText("");
        nama.setText("");
        email.setText("");
        password.setText("");
        setLevel();
    }
    
    private void setLevel(){
        level.setValue("Operator");
        level.setItems(comboPilihan);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clear();
    }    
    
    @FXML
    private void tambahClicked(){
        if(username.getText().equals("")||password.getText().equals("")||
                nama.getText().equals("")||email.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Lengkapi data terlebih dahulu !!");
        }
        else{
            model.cekUsername(username.getText());
            if(model.getRowUsername()==1){
                nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Username sudah terdaftar..\nSilahkan gunakan username yang lain..");
                username.setText("");
                username.requestFocus();
            }
            else{
                model.insertUserManagement(username.getText(), password.getText(),
                        nama.getText(), email.getText(), 
                        level.getSelectionModel().getSelectedItem().toString());
                if(model.getStatusInsert()==true){
                    nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "User baru telah tersimpan..");
                    clear();
                }
                else{
                    nav.showAlert(Alert.AlertType.ERROR, "Error", null, "User gagal ditambahkan...");
                }
            }
        }
    }
    
}
