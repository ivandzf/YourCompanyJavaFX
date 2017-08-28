package controller;

import function.navigation;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import model.uangKeluarModel;

public class TambahUangKeluarController implements Initializable {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    uangKeluarModel model = new uangKeluarModel();
       
    @FXML
    private TextField detail_uang_keluar,uang_keluar,date_uang_keluar_text;
    
    @FXML
    private DatePicker date_uang_keluar;
   
     
    private void clear(){
        detail_uang_keluar.setText("");
        uang_keluar.setText("");
        //date_uang_keluar_text.setText("");
        detail_uang_keluar.requestFocus();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nav.harusAngka(uang_keluar);
        detail_uang_keluar.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
        uang_keluar.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
    }    
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(date_uang_keluar.getValue()));
        date_uang_keluar_text.setText(dateText);
    }
    
    @FXML
    private void insertClicked(ActionEvent event){
        inputMethod();
    }
    
    private void inputMethod(){
        if(detail_uang_keluar.getText().equals("")||uang_keluar.getText().equals("")||date_uang_keluar_text.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Lengkapi data terlebih dahulu !!");
        }
        else{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String detail=detail_uang_keluar.getText();
            String uang=uang_keluar.getText();
            String date_text=date_uang_keluar_text.getText();
            String date=date_uang_keluar.getValue().toString();
            String waktu_input=dateFormat.format(cal.getTime());
            model.insertUangKeluar(detail, uang, date, waktu_input);
            if(model.getStatusInert()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "Uang keluar telah tersimpan..");
                clear();
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Uang keluar gagal tersimpan..");
            }
        }
    }
    
}
