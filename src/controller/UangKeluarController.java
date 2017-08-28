package controller;

import animasi.FadeInRightTransition;
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
import javax.swing.JOptionPane;
import model.uangKeluarModel;
import model.uangKeluarTable;

public class UangKeluarController implements Initializable {
    
    ObservableList<String> comboFilter = FXCollections.observableArrayList("Hari","Bulan","Semua");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January","February"
            ,"March","April","May","June","July","August","September","October","November","December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    
    @FXML
    private TableView<uangKeluarTable> tableUangKeluar;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnID;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnNo;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnDetail;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnUang;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnUangBiasa;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnPilih;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnTanggal;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnTanggalBiasa;
    
    @FXML
    private TableColumn<uangKeluarTable, String> columnWaktuInput;
    
    private ObservableList<uangKeluarTable> data;
    
    @FXML
    private AnchorPane utama;
    
    @FXML
    private AnchorPane blur;
    
    @FXML
    private AnchorPane loadPane;
    
    @FXML
    private AnchorPane dataUangKeluar;
    
    @FXML
    private StackPane trans;
    
    @FXML
    private Group groups;
    
    @FXML
    private ContextMenu contextMenu;
    
    @FXML
    private CheckBox check;
    
    @FXML
    private ComboBox filter, bulan;
    
    @FXML
    private TextField hari, tahun, cari;
    
    @FXML
    private DatePicker hari_pilih;
    
    private String id="", detail="", uang="", tanggal="", tanggalBiasa="";
    
    koneksi kon = new koneksi();
    uangKeluarModel model = new uangKeluarModel();
    NumberFormat num = NumberFormat.getInstance();
    navigation nav = new navigation();
    time time = new time();
    
    private void setFilter(){
        filter.setValue("Hari");
        filter.setItems(comboFilter);
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
        columnUang.setStyle("-fx-alignment: CENTER");
        columnTanggal.setStyle("-fx-alignment: CENTER");
        columnWaktuInput.setStyle("-fx-alignment: CENTER");
    }
    
    private void loadTable(){
        try {
            nav.animationFade(tableUangKeluar);
            if(filter.getSelectionModel().getSelectedItem().toString().equals("Hari")){
                model.filterHari(hari_pilih.getValue().toString(),cari.getText());
            }
            else if(filter.getSelectionModel().getSelectedItem().toString().equals("Bulan")){
                model.filterBulan(bulan.getSelectionModel().getSelectedItem().toString(), tahun.getText(),cari.getText());
            }
            else{
                model.filterSemua(cari.getText());
            }
            int no=0;
            data=FXCollections.observableArrayList();
            kon.res=kon.stat.executeQuery(model.queryLoad);
            while(kon.res.next()){
                no++;
                String uangFormat=num.format(Integer.parseInt(kon.res.getString(3).toString()));
                data.add(new uangKeluarTable(kon.res.getString(1),String.valueOf(no),
                        kon.res.getString(2), uangFormat,
                        kon.res.getString(4), kon.res.getString(6), kon.res.getString(3), kon.res.getString(5)));
            }
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnNo.setCellValueFactory(new PropertyValueFactory<>("no"));
            columnDetail.setCellValueFactory(new PropertyValueFactory<>("detail"));
            columnUang.setCellValueFactory(new PropertyValueFactory<>("uang"));
            columnUangBiasa.setCellValueFactory(new PropertyValueFactory<>("uangBiasa"));
            columnTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
            columnTanggalBiasa.setCellValueFactory(new PropertyValueFactory<>("tanggalBiasa"));
            columnWaktuInput.setCellValueFactory(new PropertyValueFactory<>("waktuInput"));
            tableUangKeluar.setItems(null);
            tableUangKeluar.setItems(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void clearParameter(){
        id="";
        detail="";
        uang="";
        tanggal="";
    }
    
    @FXML
    private void refreshClicked(ActionEvent event){
        loadTable();
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFilter();
        setHari();
        setBulan();
        setTahun();
        setStyleTable();
        kon.db();
        num.setMaximumFractionDigits(3);
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
    private void ambilID(MouseEvent event) throws IOException{
        if(event.getClickCount()==1){
            id = tableUangKeluar.getSelectionModel().getSelectedItem().getId();
            detail = tableUangKeluar.getSelectionModel().getSelectedItem().getDetail();
            uang = tableUangKeluar.getSelectionModel().getSelectedItem().getUangBiasa();
            tanggal = tableUangKeluar.getSelectionModel().getSelectedItem().getTanggal();
            tanggalBiasa = tableUangKeluar.getSelectionModel().getSelectedItem().getTanggalBiasa();
        }
        else if(event.getClickCount()==2){
            id = tableUangKeluar.getSelectionModel().getSelectedItem().getId();
            detail = tableUangKeluar.getSelectionModel().getSelectedItem().getDetail();
            uang = tableUangKeluar.getSelectionModel().getSelectedItem().getUangBiasa();
            tanggal = tableUangKeluar.getSelectionModel().getSelectedItem().getTanggal();
            tanggalBiasa = tableUangKeluar.getSelectionModel().getSelectedItem().getTanggalBiasa();
            openUbah();
        }
        else if (event.getButton()==MouseButton.SECONDARY){
            contextMenu.show(tableUangKeluar, event.getScreenX(), event.getScreenY());
        }
    }
    
    @FXML
    private void tambahClicked() throws IOException{
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getTambahUangKeluar()));
        loadPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void ubahClicked(ActionEvent event) throws IOException {
        if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Silahkan pilih data pada tabel uang keluar..");
        }
        else{
            openUbah();
        }
    }
    
    @FXML
    private void hapusClicked(ActionEvent event) throws IOException{
        if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Silahkan pilih data pada tabel uang keluar..");
        }
        else{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hapus data uang keluar");
        alert.setHeaderText("Detail\t\t: "+detail
                +"\nUang Keluar\t: "+uang
                +"\nTanggal\t\t: "+tanggal);
        alert.setContentText("Anda yakin ingin menghapus data ini ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            model.deleteUangKeluar(id);
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
    
    private void openUbah() throws IOException{
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getUbahUangKeluar()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        UbahUangKeluarController ubahUangKeluar = Loader.getController();
        ubahUangKeluar.setData(id, detail, uang, tanggal, tanggalBiasa);
        loadPane.getChildren().setAll(pane);
    }
    
}
