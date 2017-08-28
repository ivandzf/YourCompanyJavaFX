package controller;

import animasi.FadeInRightTransition;
import animasi.FadeInTransition;
import animasi.FadeOutLeftTransition;
import db.koneksi;
import function.navigation;
import function.time;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import model.uangMasukModel;
import model.uangMasukTable;

public class UangMasukController implements Initializable {
    
    ObservableList<String> comboFilter = FXCollections.observableArrayList("Hari","Bulan","Semua");
    ObservableList<String> comboJenis = FXCollections.observableArrayList("Semua","Bakul","Hotel");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January","February"
            ,"March","April","May","June","July","August","September","October","November","December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    
    @FXML
    private TableView<uangMasukTable> tableUangMasuk;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnID;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnNo;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnDetail;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnDebit;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnDebitBiasa;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnSaldo;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnKredit;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnKreditBiasa;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnPilih;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnTanggal;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnTanggalBiasa;
    
    @FXML
    private TableColumn<uangMasukTable, String> columnWaktuInput;
    
    private ObservableList<uangMasukTable> data;
    
    @FXML
    private AnchorPane utama;
    
    @FXML
    private AnchorPane blur;
    
    @FXML
    private AnchorPane loadPane;
    
    @FXML
    private AnchorPane dataUangMasuk;
    
    @FXML
    private StackPane trans;
    
    @FXML
    private Group groups;
    
    @FXML
    private ContextMenu contextMenu;
    
    @FXML
    private CheckBox check;
    
    @FXML
    private ComboBox filter, bulan, jenis;
    
    @FXML
    private TextField hari, tahun, cari;
    
    @FXML
    private DatePicker hari_pilih;
    
    private String id="", detail="", debit="", kredit="", pilih="", tanggal="", tanggalBiasa="";
    
    koneksi kon = new koneksi();
    uangMasukModel model = new uangMasukModel();
    NumberFormat num = NumberFormat.getInstance();
    navigation nav = new navigation();
    time time = new time();
    
    private void setFilter(){
        filter.setValue("Hari");
        filter.setItems(comboFilter);
    }
    private void setJenis(){
        jenis.setValue("Semua");
        jenis.setItems(comboJenis);
    }
    
    private void setHari(){
        hari.setText(time.tanggal());
        hari_pilih.setValue(LocalDate.parse(time.tanggalQuery()));
    }
    
    private void setBulan(){
        bulan.setValue(time.tanggalBulan());
        bulan.setItems(comboBulan);
    }
    
    private void setTahun(){
        tahun.setText(time.tanggalTahun());
    }
    
    private void setStyleTable(){
        
        columnNo.setStyle("-fx-alignment: CENTER");
        columnDebit.setStyle("-fx-alignment: CENTER");
        columnKredit.setStyle("-fx-alignment: CENTER");
        columnSaldo.setStyle("-fx-alignment: CENTER");
        columnPilih.setStyle("-fx-alignment: CENTER");
        columnTanggal.setStyle("-fx-alignment: CENTER");
        columnWaktuInput.setStyle("-fx-alignment: CENTER");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFilter();
        setJenis();
        setHari();
        setBulan();
        setTahun();
        setStyleTable();
        kon.db();
        num.setMaximumFractionDigits(3);
        loadTable();
    }    
    
    private void clearParameter(){
        id="";
        detail="";
        debit="";
        kredit="";
        pilih="";
        tanggal="";
    }
    
    private void loadTable(){
        try {
            nav.animationFade(tableUangMasuk);
            if(filter.getSelectionModel().getSelectedItem().toString().equals("Hari")){
                model.filterHari(hari_pilih.getValue().toString(),cari.getText(),jenis.getSelectionModel().getSelectedItem().toString());
            }
            else if(filter.getSelectionModel().getSelectedItem().toString().equals("Bulan")){
                model.filterBulan(bulan.getSelectionModel().getSelectedItem().toString(), tahun.getText(),cari.getText(),jenis.getSelectionModel().getSelectedItem().toString());
            }
            else{
                model.filterSemua(cari.getText(),jenis.getSelectionModel().getSelectedItem().toString());
            }
            int no=0;
            data=FXCollections.observableArrayList();
            kon.res=kon.stat.executeQuery(model.queryLoad);
            while(kon.res.next()){
                no++;
                String debitFormat=num.format(Integer.parseInt(kon.res.getString(3).toString()));
                String kreditFormat=num.format(Integer.parseInt(kon.res.getString(4).toString()));
                String saldoFormat=num.format(Integer.parseInt(kon.res.getString(9).toString()));
                data.add(new uangMasukTable(kon.res.getString(1),String.valueOf(no),
                        kon.res.getString(2),kon.res.getString(5),kon.res.getString(6), debitFormat, kreditFormat, saldoFormat,
                        kon.res.getString(8), kon.res.getString(3), kon.res.getString(4), kon.res.getString(7)));
            }
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnNo.setCellValueFactory(new PropertyValueFactory<>("no"));
            columnDetail.setCellValueFactory(new PropertyValueFactory<>("detail"));
            columnDebit.setCellValueFactory(new PropertyValueFactory<>("debit"));
            columnDebitBiasa.setCellValueFactory(new PropertyValueFactory<>("debitBiasa"));
            columnKredit.setCellValueFactory(new PropertyValueFactory<>("kredit"));
            columnKreditBiasa.setCellValueFactory(new PropertyValueFactory<>("kreditBiasa"));
            columnSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
            columnPilih.setCellValueFactory(new PropertyValueFactory<>("pilih"));
            columnTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
            columnTanggalBiasa.setCellValueFactory(new PropertyValueFactory<>("tanggalBiasa"));
            columnWaktuInput.setCellValueFactory(new PropertyValueFactory<>("waktuInput"));
            tableUangMasuk.setItems(null);
            tableUangMasuk.setItems(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    private void filterClicked(ActionEvent event){
        if(filter.getSelectionModel().getSelectedItem().toString().equals("Hari")){
            bulan.setVisible(false);
            hari.setVisible(true);
            hari_pilih.setVisible(true);
            tahun.setVisible(false);
            setHari();
        }
        else if (filter.getSelectionModel().getSelectedItem().toString().equals("Bulan")){
            bulan.setVisible(true);
            hari.setVisible(false);
            hari_pilih.setVisible(false);
            tahun.setVisible(true);
            setBulan();
            setTahun();
        }
        else{
            bulan.setVisible(false);
            hari.setVisible(false);
            hari_pilih.setVisible(false);
            tahun.setVisible(false);
        }
    }
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(hari_pilih.getValue()));
        hari.setText(dateText);
    }
    
    @FXML
    private void checkClicked(ActionEvent event){
        if(check.isSelected()==true){
            columnWaktuInput.setVisible(true);
        }
        else{
            columnWaktuInput.setVisible(false);
        }
    }
    
    @FXML
    private void refreshClicked(){
        loadTable();
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getTambahUangMasuk()));
        loadPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void ambilId(MouseEvent event) throws IOException {
        if(event.getClickCount()==1){
            id = tableUangMasuk.getSelectionModel().getSelectedItem().getId();
            detail = tableUangMasuk.getSelectionModel().getSelectedItem().getDetail();
            debit = tableUangMasuk.getSelectionModel().getSelectedItem().getDebitBiasa();
            kredit = tableUangMasuk.getSelectionModel().getSelectedItem().getKreditBiasa();
            pilih = tableUangMasuk.getSelectionModel().getSelectedItem().getPilih();
            tanggal = tableUangMasuk.getSelectionModel().getSelectedItem().getTanggal();
            tanggalBiasa = tableUangMasuk.getSelectionModel().getSelectedItem().getTanggalBiasa();
        }
        else if(event.getClickCount()==2){
            id = tableUangMasuk.getSelectionModel().getSelectedItem().getId();
            detail = tableUangMasuk.getSelectionModel().getSelectedItem().getDetail();
            debit = tableUangMasuk.getSelectionModel().getSelectedItem().getDebitBiasa();
            kredit = tableUangMasuk.getSelectionModel().getSelectedItem().getKreditBiasa();
            pilih = tableUangMasuk.getSelectionModel().getSelectedItem().getPilih();
            tanggal = tableUangMasuk.getSelectionModel().getSelectedItem().getTanggal();
            tanggalBiasa = tableUangMasuk.getSelectionModel().getSelectedItem().getTanggalBiasa();
            openUbah();
        }
        else if (event.getButton()==MouseButton.SECONDARY){
            contextMenu.show(tableUangMasuk, event.getScreenX(), event.getScreenY());
        }
    }
    
    @FXML
    private void ubahClicked(ActionEvent event) throws IOException {
        if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Silahkan pilih data pada tabel uang masuk..");
        }
        else{
            openUbah();
        }
    }
    
    @FXML
    private void hapusClicked(ActionEvent event) throws IOException{
        if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Silahkan pilih data pada tabel uang masuk..");
        }
        else{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hapus data uang masuk");
        alert.setHeaderText("Detail\t\t: "+detail
                +"\nDebit\t\t: "+num.format(Integer.parseInt(id))
                +"\nKredit\t\t: "+kredit
                +"\nSaldo\t\t: "+(Integer.parseInt(debit)-Integer.parseInt(kredit))
                +"\nJenis\t\t\t: "+pilih
                +"\nTanggal\t\t: "+tanggal);
        alert.setContentText("Anda yakin ingin menghapus data ini ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            model.deleteUangMasuk(id);
            if(model.getDelete()==true){
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
    
    private void openUbah() throws IOException{
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getUbahUangMasuk()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        UbahUangMasukController ubahUangMasuk = Loader.getController();
        ubahUangMasuk.setData(id, detail, debit, kredit, tanggal, pilih, tanggalBiasa);
        loadPane.getChildren().setAll(pane);
    }
    
    
    
}
