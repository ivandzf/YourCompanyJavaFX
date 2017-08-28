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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.userManagementModel;

public class UbahUserManagementController implements Initializable {

    navigation nav = new navigation();
    userManagementModel model = new userManagementModel();
    ObservableList<String> comboPilihan = FXCollections.observableArrayList("Operator","User");
    ObservableList<String> comboPilihan2 = FXCollections.observableArrayList("Login","Tidak Login");
    
    @FXML
    private TextField username, nama, email;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private ComboBox level,status;
    
    @FXML
    private Label id;
    
    private void setLevel(){
        level.setValue("Operator");
        level.setItems(comboPilihan);
    }
    private void setStatus(){
        status.setValue("Login");
        status.setItems(comboPilihan2);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLevel();
        setStatus();
    }    
        
    public void setData(String id, String username, String password, String nama
                        ,String email, String level, String status){
        this.id.setText(id);
        this.username.setText(username);
        this.password.setText(password);
        this.nama.setText(nama);
        this.email.setText(email);
        this.level.setValue(level);
        this.status.setValue(status);
    }
    
    @FXML
    private void simpanClicked(){
        if(password.getText().equals("")||nama.getText().equals("")||email.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Lengkapi data terlebih dahulu !!");
        }
        else{
            model.updateUserManagement(id.getText(), password.getText(),
                    nama.getText(), email.getText(), 
                    level.getSelectionModel().getSelectedItem().toString(),
                    status.getSelectionModel().getSelectedItem().toString());
            if(model.getStatusUpdate()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "User berhasil disimpan...");
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "User gagal disimpan...");
            }
        }
    }
    
}
