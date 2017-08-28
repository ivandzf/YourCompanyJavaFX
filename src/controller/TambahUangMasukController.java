package controller;

import function.navigation;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import model.uangMasukModel;

public class TambahUangMasukController implements Initializable {

    ObservableList<String> comboPilihan = FXCollections.observableArrayList("Bakul","Hotel");
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    uangMasukModel model = new uangMasukModel();
    navigation nav = new navigation();
    
    @FXML
    private DatePicker date_uang_masuk;
    
    @FXML
    private ComboBox pilih_uang_masuk;
    
    @FXML
    private TextField detail_uang_masuk, debit, kredit, date_uang_masuk_text;
            
    @FXML
    private AnchorPane pane;
    
    private void clear(){
        detail_uang_masuk.setText("");
        debit.setText("");
        kredit.setText("");
        //date_uang_masuk_text.setText("");
        //date_uang_masuk.setValue(null);
        detail_uang_masuk.requestFocus();
    }
    
    private void setPilihan(){
        pilih_uang_masuk.setValue("Bakul");
        pilih_uang_masuk.setItems(comboPilihan);
    }
            
    private void setKredit(){
        debit.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!debit.getText().matches("[0-9]*")){
                    nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Hanya boleh angka !!");
                    debit.setText("");
                    debit.requestFocus();
                }
                kredit.setText(debit.getText());
            }
        });
    }
    private void setKreditMax(){
        kredit.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!kredit.getText().matches("[0-9]*")){
                    nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Hanya boleh angka !!");
                    kredit.setText("");
                    kredit.requestFocus();
                }
                if(!kredit.getText().equals("")){
                    int debitValue=Integer.parseInt(debit.getText());
                    int kreditValue=Integer.parseInt(kredit.getText());
                    if(kreditValue>debitValue){
                        nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Kredit tidak boleh lebih besar dari Debit !!");
                        kredit.setText("");
                        kredit.requestFocus();
                    }
                }
            }
        });
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setKredit();
        setKreditMax();
        clear();
        setPilihan();
        detail_uang_masuk.requestFocus();
        detail_uang_masuk.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
        debit.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
        kredit.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
    }    
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(date_uang_masuk.getValue()));
        date_uang_masuk_text.setText(dateText);
    }
    
    @FXML
    private void inputClicked(ActionEvent event){
        inputMethod();
    }
    
    private void inputMethod(){
        if(detail_uang_masuk.getText().equals("")||debit.getText().equals("")
                ||date_uang_masuk_text.getText().equals("")||kredit.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Lengkapi data terlebih dahulu !!");
        }
        else{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String detailText=detail_uang_masuk.getText();
            String debitText=debit.getText();
            String kreditText=kredit.getText();
            String tanggalText=date_uang_masuk.getValue().toString();
            String pilihText=pilih_uang_masuk.getSelectionModel().getSelectedItem().toString();
            String waktuInputText=dateFormat.format(cal.getTime());
            model.insertUangMasuk(detailText, debitText, kreditText, pilihText, tanggalText, waktuInputText);
            if(model.getInsert()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "Uang masuk telah tersimpan..");
                clear();
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Uang masuk gagal tersimpan..");
            }
        }
    }
    
}
