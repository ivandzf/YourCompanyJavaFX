package controller;

import function.navigation;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.uangKeluarModel;

public class UbahUangKeluarController implements Initializable {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    uangKeluarModel model = new uangKeluarModel();
       
    @FXML
    private TextField detail_uang_keluar,uang_keluar,date_uang_keluar_text;
    
    @FXML
    private DatePicker date_uang_keluar;
    
    @FXML
    private Label idUangKeluar;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nav.harusAngka(uang_keluar);
    }    
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(date_uang_keluar.getValue()));
        date_uang_keluar_text.setText(dateText);
    }
    
    public void setData(String id, String detail, String uang, String dateText, String date){
        idUangKeluar.setText(id);
        detail_uang_keluar.setText(detail);
        uang_keluar.setText(uang);
        date_uang_keluar_text.setText(dateText);
        date_uang_keluar.setValue(LocalDate.parse(date));
    }
    
    @FXML
    private void simpanClicked(ActionEvent event){
       if(detail_uang_keluar.getText().equals("")||uang_keluar.getText().equals("")||date_uang_keluar_text.getText().equals("")){
           nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Lengkapi data terlebih dahulu !!");
       } 
       else{
           String idText=idUangKeluar.getText();
           String detailText=detail_uang_keluar.getText();
           String uangText=uang_keluar.getText();
           String tanggalText=date_uang_keluar.getValue().toString();
           model.updateUangKeluar(idText, detailText, uangText, tanggalText);
           if(model.getStatusUpdate()==true){
               nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "Uang keluar telah disimpan..");
           }
           else{
               nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Uang masuk gagal disimpan..");
           }
       }
    }
    
}
