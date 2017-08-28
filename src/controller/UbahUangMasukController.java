package controller;

import function.navigation;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import model.uangMasukModel;


public class UbahUangMasukController implements Initializable {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    
    ObservableList<String> comboPilihan = FXCollections.observableArrayList("Bakul","Hotel");
    
    @FXML
    private Label idUangMasuk;
    
    @FXML
    private DatePicker date_uang_masuk;
    
    @FXML
    private ComboBox pilih_uang_masuk;
    
    @FXML
    private TextField detail_uang_masuk, debit, kredit, date_uang_masuk_text;
    
    navigation nav = new navigation();
    uangMasukModel model = new uangMasukModel();
                    
    private void setPilihan(){
        pilih_uang_masuk.setValue("Bakul");
        pilih_uang_masuk.setItems(comboPilihan);
    }
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(date_uang_masuk.getValue()));
        date_uang_masuk_text.setText(dateText);
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
        setPilihan();
    }    
            
    public void setData(String id, String detail, String debittext, String kreditext, String tanggal, String pilih, String tanggalBiasa){
        idUangMasuk.setText(id);
        detail_uang_masuk.setText(detail);
        debit.setText(debittext);
        kredit.setText(kreditext);
        date_uang_masuk_text.setText(tanggal);
        pilih_uang_masuk.setValue(pilih);
        date_uang_masuk.setValue(LocalDate.parse(tanggalBiasa));
    }
    
    @FXML
    private void simpanClicked(ActionEvent event) throws IOException, ParseException{
        if(detail_uang_masuk.getText().equals("")||debit.getText().equals("")||kredit.getText().equals("")
                ||date_uang_masuk_text.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Lengkapi data terlebih dahulu !!");
        }
        else{
            String idText=idUangMasuk.getText();
            String detailText=detail_uang_masuk.getText();
            String debitText=debit.getText();
            String kreditText=kredit.getText();
            String tanggalText=date_uang_masuk.getValue().toString();
            String pilihText=pilih_uang_masuk.getSelectionModel().getSelectedItem().toString();
            model.updateUangMasuk(idText, detailText, debitText, kreditText, pilihText, tanggalText);
            if(model.getUpdate()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "Uang masuk telah disimpan..");
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Uang masuk gagal disimpan..");
            }
        }
    }
    
    
}
