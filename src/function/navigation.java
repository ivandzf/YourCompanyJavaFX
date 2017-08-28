package function;

import controller.HomeController;
import java.awt.event.KeyEvent;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class navigation {

    private final String login="/view/Login.fxml";
    private final String home="/view/Home.fxml";
    private final String dashboard="/view/Dashboard.fxml";
    private final String database="/view/Database.fxml";
    private final String uangMasuk="/view/UangMasuk.fxml";
    private final String tambahUangMasuk="/view/TambahUangMasuk.fxml";
    private final String ubahUangMasuk="/view/UbahUangMasuk.fxml";
    private final String user="/view/User.fxml";
    private final String uangKeluar="/view/UangKeluar.fxml";
    private final String tambahUangKeluar="/view/TambahUangKeluar.fxml";
    private final String ubahUangKeluar="/view/UbahUangKeluar.fxml";
    private final String userManagement="/view/UserManagement.fxml";
    private final String tambahUserManagement="/view/TambahUserManagement.fxml";
    private final String ubahUserManagement="/view/UbahUserManagement.fxml";
    private final String laporanUangMasukHarian="/view/LaporanUangMasukHarian.fxml";
    private final String laporanUangMasukBulanan="/view/LaporanUangMasukBulanan.fxml";
    private final String laporanUangKeluarHarian="/view/LaporanUangKeluarHarian.fxml";
    private final String laporanUangKeluarBulanan="/view/LaporanUangKeluarBulanan.fxml";
    private String username,nama,email;
    
    public Image applicationIcon = new Image(getClass().getResourceAsStream("/img/icons8_Source_Code_104px_2.png"));
    
    public String getHome(){
        return home;
    }
    
    public String getLogin(){
        return login;
    }
    
    public String getDashboard(){
        return dashboard;
    }
    
    public String getDatabase(){
        return database;
    }
    
    public String getUangMasuk(){
        return uangMasuk;
    }
    
    public String getTambahUangMasuk(){
        return tambahUangMasuk;
    }
    
    public String getUbahUangMasuk(){
        return ubahUangMasuk;
    }
    
    public String getTambahUangKeluar(){
        return tambahUangKeluar;
    }
    
    public String getUbahUangKeluar(){
        return ubahUangKeluar;
    }
    
    public String getUserManagement(){
        return userManagement;
    }
    
    public String getTambahUserManagement(){
        return tambahUserManagement;
    }
    
    public String getUbahUserManagement(){
        return ubahUserManagement;
    }
    
    public String getLaporanUangMasukHarian(){
        return laporanUangMasukHarian;
    }
    
    public String getLaporanUangMasukBulanan(){
        return laporanUangMasukBulanan;
    }
    
    public String getLaporanUangKeluarHarian(){
        return laporanUangKeluarHarian;
    }
    
    public String getLaporanUangKeluarBulanan(){
        return laporanUangKeluarBulanan;
    }
    
    public String getUser(){
        return user;
    }
    
    public String getUangKeluar(){
        return uangKeluar;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public void setNama(String nama){
        this.nama=nama;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void showAlert(AlertType type, String title, String header, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
        
    public void harusAngka(TextField text){
        text.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!text.getText().matches("[0-9]*")){
                    showAlert(AlertType.WARNING, "Peringatan", null, "Hanya boleh angka !!");
                    text.setText("");
                    text.requestFocus();
                }
            }
        });
        
    }
            
    public void animationFade(Node e){
        FadeTransition x = new FadeTransition(new Duration(1000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }
    
}
