package controller;

import animasi.FadeInRightTransition;
import animasi.FadeOutLeftTransition;
import db.koneksi;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;
import model.userManagementModel;
import model.userManagementTable;

public class UserManagementController implements Initializable {
    
    @FXML
    private TableView<userManagementTable> tableUser;
    
    @FXML
    private TableColumn<userManagementTable, String> columnID;
    
    @FXML
    private TableColumn<userManagementTable, String> columnNo;
    
    @FXML
    private TableColumn<userManagementTable, String> columnUsername;
    
    @FXML
    private TableColumn<userManagementTable, String> columnPassword;
    
    @FXML
    private TableColumn<userManagementTable, String> columnNama;
    
    @FXML
    private TableColumn<userManagementTable, String> columnEmail;
    
    @FXML
    private TableColumn<userManagementTable, String> columnLevel;
    
    @FXML
    private TableColumn<userManagementTable, String> columnStatus;
        
    private ObservableList<userManagementTable> data;
    
    @FXML
    private AnchorPane utama;
    
    @FXML
    private AnchorPane blur;
    
    @FXML
    private AnchorPane loadPane;
    
    @FXML
    private AnchorPane dataUser;
    
    @FXML
    private StackPane trans;
    
    @FXML
    private Group groups;
    
    @FXML
    private ContextMenu contextMenu;
    
    @FXML
    private CheckBox check;
    
    private String id="", username="", password="", nama="", email="", level="", status="";
    
    koneksi kon = new koneksi();
    navigation nav = new navigation();
    userManagementModel model = new userManagementModel();
    
    private void clearParameter(){
        id="";
        username="";
        password="";
        nama="";
        email="";
        level="";
        status="";
    }
    
    private void setStyleTable(){
        columnNo.setStyle("-fx-alignment: CENTER");
        columnLevel.setStyle("-fx-alignment: CENTER");
        columnStatus.setStyle("-fx-alignment: CENTER");
    }
    
    private void loadTable(){
        try {
            int no=0;
            data=FXCollections.observableArrayList();
            kon.res=kon.stat.executeQuery(model.queryLoad);
            while(kon.res.next()){
                no++;
                data.add(new userManagementTable(kon.res.getString(1), String.valueOf(no)
                        , kon.res.getString(2), kon.res.getString(3), kon.res.getString(4)
                        , kon.res.getString(5), kon.res.getString(6), kon.res.getString(7)));
            }
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnNo.setCellValueFactory(new PropertyValueFactory<>("no"));
            columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            columnNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            tableUser.setItems(null);
            tableUser.setItems(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void openUbah() throws IOException{
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getUbahUserManagement()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        UbahUserManagementController ubahUserManagement = Loader.getController();
        ubahUserManagement.setData(id, username, password, nama, email, level, status);
        loadPane.getChildren().setAll(pane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setStyleTable();
        kon.db();
        loadTable();
    }    
    
    @FXML
    private void ambilId(MouseEvent event) throws IOException {
        if(event.getClickCount()==1){
            id = tableUser.getSelectionModel().getSelectedItem().getId();
            username = tableUser.getSelectionModel().getSelectedItem().getUsername();
            password = tableUser.getSelectionModel().getSelectedItem().getPassword();
            nama = tableUser.getSelectionModel().getSelectedItem().getNama();
            email = tableUser.getSelectionModel().getSelectedItem().getEmail();
            level = tableUser.getSelectionModel().getSelectedItem().getLevel();
            status = tableUser.getSelectionModel().getSelectedItem().getStatus();
        }
        else if(event.getClickCount()==2){
            id = tableUser.getSelectionModel().getSelectedItem().getId();
            username = tableUser.getSelectionModel().getSelectedItem().getUsername();
            password = tableUser.getSelectionModel().getSelectedItem().getPassword();
            nama = tableUser.getSelectionModel().getSelectedItem().getNama();
            email = tableUser.getSelectionModel().getSelectedItem().getEmail();
            level = tableUser.getSelectionModel().getSelectedItem().getLevel();
            status = tableUser.getSelectionModel().getSelectedItem().getStatus();
            openUbah();
        }
        else if (event.getButton()==MouseButton.SECONDARY){
            contextMenu.show(tableUser, event.getScreenX(), event.getScreenY());
        }
    }
    
    @FXML
    private void tombolClose(ActionEvent event){
        loadTable();
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        clearParameter();
    }
    
    @FXML
    private void tambahClicked() throws IOException{
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getTambahUserManagement()));
        loadPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void ubahClicked() throws IOException{
        if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Silahkan pilih data pada tabel user..");
        }
        else{
            openUbah();
        }
    }
    
    @FXML
    private void hapusClicked(ActionEvent event) throws IOException{
        if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Silahkan pilih data pada tabel user..");
        }
        else{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hapus data user");
        alert.setHeaderText("Username\t: "+username
                +"\nPassword\t\t: "+password+"\nNama\t\t: "+nama
                +"\nEmail\t\t: "+email+"\nLevel\t\t: "+level);
        alert.setContentText("Anda yakin ingin menghapus data ini ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            model.deleteUserManagement(id);
            if(model.getStatusDelete()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "Data berhasil dihapus..");
                loadTable();
                clearParameter();
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Data gagal dihapus..");
            }
        } 
        }
    }
    
    
}
