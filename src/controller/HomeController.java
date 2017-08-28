package controller;

import animasi.FadeInRightTransition;
import animasi.FadeInTransition;
import animasi.FadeOutLeftTransition;
import function.exit;
import function.mouseDrag;
import function.navigation;
import function.time;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import model.homeModel;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class HomeController implements Initializable {
    navigation nav = new navigation(); 
    homeModel model = new homeModel();
    exit ex = new exit();
    private String username_text,email_text,nama_text;
    
    TrayNotification tray = new TrayNotification();
    
    @FXML
    private Label minimize, exit, tanggal, jam, emailUser, namaUser, usernameUser, idUser, levelUser;
    
    @FXML
    private Button btn_home, btn_home1, btn_home11;
    
    @FXML
    private Button btn_uang_masuk, btn_laporan_uang_masuk_bln, btn_laporan_uang_masuk_hr, btn_laporan_uang_masuk_bln1, btn_laporan_uang_masuk_hr1;
    
    @FXML
    private Button btn_uang_keluar, btn_laporan_uang_keluar_hr, btn_laporan_uang_keluar_bln, btn_laporan_uang_keluar_hr1, btn_laporan_uang_keluar_bln1;
    
    @FXML
    private Button btn_user_management;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private AnchorPane loadPane,blur, cobain;
    
    @FXML
    private StackPane trans;
    
    @FXML
    private ImageView imageUser;
    
    @FXML
    private VBox admin, operator, user;
        
    public static String menu;
    
    private void bindToTime() {
    Timeline timeline = new Timeline(
    new KeyFrame(Duration.seconds(0),
      new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent actionEvent) {
          Calendar time = Calendar.getInstance();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
          jam.setText(simpleDateFormat.format(time.getTime()));
        }
      }
    ),
    new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    
    
    @FXML
    private void setHover(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color: red;");
    }
        
    @FXML
    private void setDefault(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color:  #4183D7;");
    }
    @FXML
    private void setHover2(javafx.scene.input.MouseEvent event){
        minimize.setStyle("-fx-background-color: red;");
    }
        
    @FXML
    private void setDefault2(javafx.scene.input.MouseEvent event){
        minimize.setStyle("-fx-background-color:  #4183D7;");
    }
    
    @FXML
    private void imageHover(MouseEvent event){
        imageUser.setCursor(Cursor.HAND);
    }
    
    @FXML
    private void handleExitClicked(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Anda yakin ingin keluar dari aplikasi ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            model.setStatus(idUser.getText());
            System.exit(0);
        } 
    }
    @FXML
    private void handleMinimizeClicked(MouseEvent event){
        ex.minimizeClicked(event);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindToTime();
        time time = new time();
        tanggal.setText(time.tanggal());
        imageUser.setCursor(Cursor.HAND);
        try {
            homeMenu();
        } catch (IOException ex) { 
        }
    }    
    
    private void setMenu(){
        if(levelUser.getText().equals("Admin")){
            admin.setVisible(true);
            operator.setVisible(false);
            user.setVisible(false);
        }
        else if (levelUser.getText().equals("Operator")){
            admin.setVisible(false);
            operator.setVisible(true);
            user.setVisible(false);
        }
        else{
            admin.setVisible(false);
            operator.setVisible(false);
            user.setVisible(true);
        }
    }
    
    private void setValueModel(){
        model.setValue(idUser.getText());
        usernameUser.setText(model.getUsername());
        emailUser.setText(model.getEmail());
        namaUser.setText(model.getNama());
        levelUser.setText(model.getLevel());
        setMenu();
    }
    
    public void setValue(String id){
        idUser.setText(id);
        setValueModel();
    }
    
    @FXML
    private void setBackgroundHome(javafx.scene.input.MouseEvent event){
        btn_home.setStyle("-fx-background-color: #D2D7D3");
        btn_uang_keluar.setStyle("-fx-background-color: #ECF0F1");
        btn_uang_masuk.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangMasuk(javafx.scene.input.MouseEvent event){
        btn_uang_masuk.setStyle("-fx-background-color: #D2D7D3");
        btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_uang_keluar.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangKeluar(javafx.scene.input.MouseEvent event){
        btn_uang_keluar.setStyle("-fx-background-color: #D2D7D3");
        btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_uang_masuk.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangMasukHarian(javafx.scene.input.MouseEvent event){
        btn_uang_keluar.setStyle("-fx-background-color: #ECF0F1");
        btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_uang_masuk.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr.setStyle("-fx-background-color: #D2D7D3");
        btn_laporan_uang_keluar_hr.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangMasukBulanan(javafx.scene.input.MouseEvent event){
        btn_uang_keluar.setStyle("-fx-background-color: #ECF0F1");
        btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_uang_masuk.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln.setStyle("-fx-background-color: #D2D7D3");
        btn_laporan_uang_masuk_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangKeluarHarian(javafx.scene.input.MouseEvent event){
        btn_uang_keluar.setStyle("-fx-background-color: #ECF0F1");
        btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_uang_masuk.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr.setStyle("-fx-background-color: #D2D7D3");
        btn_laporan_uang_keluar_bln.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangKeluarBulanan(javafx.scene.input.MouseEvent event){
        btn_uang_keluar.setStyle("-fx-background-color: #ECF0F1");
        btn_home.setStyle("-fx-background-color: #ECF0F1");
        btn_uang_masuk.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln.setStyle("-fx-background-color: #D2D7D3");
    }
    
    @FXML
    private void setBackgroundUserManagement(javafx.scene.input.MouseEvent event){
        btn_home1.setStyle("-fx-background-color: #ECF0F1");
        btn_user_management.setStyle("-fx-background-color: #D2D7D3");
    }
    @FXML
    private void setBackgroundHome1(javafx.scene.input.MouseEvent event){
        btn_home1.setStyle("-fx-background-color: #D2D7D3");
        btn_user_management.setStyle("-fx-background-color: #ECF0F1");
    }
    
    @FXML
    private void setBackgroundHomeUser(javafx.scene.input.MouseEvent event){
        btn_home11.setStyle("-fx-background-color: #D2D7D3");
        btn_laporan_uang_masuk_bln1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln1.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangMasukHarianUser(javafx.scene.input.MouseEvent event){
        btn_home11.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr1.setStyle("-fx-background-color: #D2D7D3");
        btn_laporan_uang_keluar_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln1.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangMasukBulananUser(javafx.scene.input.MouseEvent event){
        btn_home11.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln1.setStyle("-fx-background-color: #D2D7D3");
        btn_laporan_uang_masuk_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln1.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangKeluarHarianUser(javafx.scene.input.MouseEvent event){
        btn_home11.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr1.setStyle("-fx-background-color: #D2D7D3");
        btn_laporan_uang_keluar_bln1.setStyle("-fx-background-color: #ECF0F1");
    }
    @FXML
    private void setBackgroundUangKeluarBulananUser(javafx.scene.input.MouseEvent event){
        btn_home11.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_bln1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_masuk_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_hr1.setStyle("-fx-background-color: #ECF0F1");
        btn_laporan_uang_keluar_bln1.setStyle("-fx-background-color: #D2D7D3");
    }
    
    @FXML
    private void logout(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Anda yakin ingin logout ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Logout Success");
            tray.setMessage("Bye "+namaUser.getText()+". Thank you for using my apps @dzf");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(1500));
            tray.setRectangleFill(Color.valueOf("#4183D7"));
            tray.setImage(new Image("/img/icons8_Male_User_100px_2.png"));
            model.setStatus(idUser.getText());
            Parent database_parent = FXMLLoader.load(getClass().getResource(nav.getLogin()));
            Scene database_scene = new Scene(database_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(database_scene);
            app_stage.setTitle("Login");
            mouseDrag md = new mouseDrag();
            md.setDragged(database_parent, app_stage);
            app_stage.getIcons().add(nav.applicationIcon);
            app_stage.show();
        } 
    }
    
    @FXML
    private void tombolClose(ActionEvent event){
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }
    
    @FXML
    private void userClicked(MouseEvent event) throws IOException{
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getUser()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        UserController userController = Loader.getController();
        userController.setValue(model.getId(), model.getUsername(), model.getPassword(), model.getNama(), model.getEmail());
        loadPane.getChildren().setAll(pane);
    }
        
    @FXML
    private void homeClicked() throws IOException{
        homeMenu();
    }
    
    @FXML
    private void uangMasukClicked() throws IOException {
        uangMasukMenu();
    }
    
    @FXML
    private void uangKeluarClicked() throws IOException{
        uangKeluarMenu();
    }
    
    @FXML
    private void laporanUangMasukHarianClicked() throws  IOException{
        laporanUangMasukHarianMenu();
    }
    
    @FXML
    private void laporanUangMasukBulananClicked() throws IOException{
        laporanUangMasukBulananMenu();
    }
    
    @FXML
    private void laporanUangKeluarHarianClicked() throws IOException{
        laporanUangKeluarHarianMenu();
    }
    
    @FXML
    private void laporanUangKeluarBulananClicked() throws IOException{
        laporanUangKeluarBulananMenu();
    }
    
    @FXML
    private void userManagementClicked() throws IOException{
        userManagementMenu();
    }
    
    public void homeMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getDashboard()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void uangMasukMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getUangMasuk()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void laporanUangMasukHarianMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getLaporanUangMasukHarian()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void laporanUangKeluarHarianMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getLaporanUangKeluarHarian()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void laporanUangMasukBulananMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getLaporanUangMasukBulanan()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void laporanUangKeluarBulananMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getLaporanUangKeluarBulanan()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void uangKeluarMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getUangKeluar()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void userManagementMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getUserManagement()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
