package controller;

import db.koneksi;
import function.navigation;
import function.time;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import model.laporanUangMasukHarianTable;
import model.uangMasukModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class LaporanUangMasukHarianController implements Initializable {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    ObservableList<String> comboJenis = FXCollections.observableArrayList("Semua","Bakul","Hotel");
    
    @FXML
    private TableView<laporanUangMasukHarianTable> tableUangMasuk;
    
    @FXML
    private TableColumn<laporanUangMasukHarianTable, String> columnNo;
    
    @FXML
    private TableColumn<laporanUangMasukHarianTable, String> columnDetail;
    
    @FXML
    private TableColumn<laporanUangMasukHarianTable, String> columnDebit;
    @FXML
    private TableColumn<laporanUangMasukHarianTable, String> columnKredit;
    @FXML
    private TableColumn<laporanUangMasukHarianTable, String> columnSaldo;
    
    @FXML
    private TableColumn<laporanUangMasukHarianTable, String> columnPilih;
    
    @FXML
    private TableColumn<laporanUangMasukHarianTable, String> columnTanggal;
    
    private ObservableList<laporanUangMasukHarianTable> data;
    
    @FXML
    private ComboBox jenis;
    
    @FXML
    private TextField hari;
    
    @FXML
    private DatePicker hari_pilih;
    
    @FXML
    private Label debitTotal, kreditTotal, saldoTotal;
    
    @FXML
    private Button btnOutput;
    
    @FXML
    private ContextMenu contextOutput;
        
    koneksi kon = new koneksi();
    uangMasukModel model = new uangMasukModel();
    NumberFormat num = NumberFormat.getInstance();
    time time = new time();
    navigation nav = new navigation();
    
    private void setJenis(){
        jenis.setValue("Semua");
        jenis.setItems(comboJenis);
    }
    
    private void setHari(){
        hari.setText(time.tanggal());
        hari_pilih.setValue(LocalDate.parse(time.tanggalQuery()));
    }
    
    private void setStyleTable(){
        columnNo.setStyle("-fx-alignment: CENTER");
        columnDebit.setStyle("-fx-alignment: CENTER");
        columnKredit.setStyle("-fx-alignment: CENTER");
        columnSaldo.setStyle("-fx-alignment: CENTER");
        columnPilih.setStyle("-fx-alignment: CENTER");
        columnTanggal.setStyle("-fx-alignment: CENTER");
    }
    
    private void loadTable(){
        try {
            nav.animationFade(tableUangMasuk);
            model.queryLaporanHarian(hari_pilih.getValue().toString(), jenis.getSelectionModel().getSelectedItem().toString());
            int no=0;
            data=FXCollections.observableArrayList();
            kon.res=kon.stat.executeQuery(model.queryLoad);
            while(kon.res.next()){
                no++;
                String debitFormat=num.format(Integer.parseInt(kon.res.getString(2).toString()));
                String kreditFormat=num.format(Integer.parseInt(kon.res.getString(3).toString()));
                String saldoFormat=num.format(Integer.parseInt(kon.res.getString(4).toString()));
                data.add(new laporanUangMasukHarianTable(String.valueOf(no),
                        kon.res.getString(1), debitFormat, kreditFormat, saldoFormat, kon.res.getString(5),
                        kon.res.getString(6)));
            }
            columnNo.setCellValueFactory(new PropertyValueFactory<>("no"));
            columnDetail.setCellValueFactory(new PropertyValueFactory<>("detail"));
            columnDebit.setCellValueFactory(new PropertyValueFactory<>("debit"));
            columnKredit.setCellValueFactory(new PropertyValueFactory<>("kredit"));
            columnSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
            columnPilih.setCellValueFactory(new PropertyValueFactory<>("pilih"));
            columnTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
            tableUangMasuk.setItems(null);
            tableUangMasuk.setItems(data);
            model.queryLaporanHarianTotal(hari_pilih.getValue().toString(), jenis.getSelectionModel().getSelectedItem().toString());
            kon.res=kon.stat.executeQuery(model.queryLoad);
            while(kon.res.next()){
                if(kon.res.getString(1)==null){
                    debitTotal.setText("-");
                }
                else{
                    debitTotal.setText(num.format(Integer.parseInt(kon.res.getString(1).toString())));
                }
                
                if(kon.res.getString(2)==null){
                    kreditTotal.setText("-");
                }
                else{
                    kreditTotal.setText(num.format(Integer.parseInt(kon.res.getString(2).toString())));
                }
                
                if(kon.res.getString(3)==null){
                    saldoTotal.setText("-");
                }
                else{
                    saldoTotal.setText(num.format(Integer.parseInt(kon.res.getString(3).toString())));
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }    
    
    @FXML
    private void printClicked(ActionEvent event){
        Properties properties = new Properties();
        JasperReport jasRep;
        JasperPrint jasPri;
        JasperDesign jasDes;
        
        try {
            properties.load(new FileInputStream("setting.properties"));
            String driverName = properties.getProperty("driverName");
            try {
                String jenis_query="";
                if(jenis.getSelectionModel().getSelectedIndex()!=0){
                    jenis_query=jenis.getSelectionModel().getSelectedItem().toString();
                }
                Class.forName(driverName);
                File report = new File("src/report/ReportUangMasukHarian.jrxml");
                jasDes = JRXmlLoader.load(report);
                HashMap parameter = new HashMap();
                parameter.put("tanggal_query", hari_pilih.getValue().toString());
                parameter.put("tanggal_text", hari.getText());
                parameter.put("jenis", jenis_query);
                parameter.put("jenis_text", jenis.getSelectionModel().getSelectedItem().toString());
                parameter.put("total_kredit", kreditTotal.getText());
                parameter.put("total_debit", debitTotal.getText());
                parameter.put("total_saldo", saldoTotal.getText());
                jasRep = JasperCompileManager.compileReport(jasDes);
                jasPri = JasperFillManager.fillReport(jasRep, parameter, kon.con);
                JasperViewer.viewReport(jasPri, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (Exception e) {
        }
    }
    
    @FXML
    private void openContext(MouseEvent event){
        contextOutput.show(btnOutput,event.getScreenX(), event.getScreenY());
    }
    
    @FXML
    private void saveXLSX(ActionEvent event) throws SQLException, FileNotFoundException, IOException{
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(hari.getText());
        XSSFRow header = sheet.createRow(1);
        XSSFCell cell = header.createCell(1);
        XSSFRow title = sheet.createRow(0);
        XSSFCell cellTitle = header.createCell(0);
        XSSFCellStyle cs = wb.createCellStyle();
        XSSFCellStyle cs2 = wb.createCellStyle();
        XSSFCellStyle cs3 = wb.createCellStyle();
        XSSFCellStyle csTitle = wb.createCellStyle();
        XSSFFont f = wb.createFont();
        XSSFFont f2 = wb.createFont();
        XSSFFont fTitle = wb.createFont();
        f.setBold(true);
        fTitle.setBold(true);
        f.setFontHeightInPoints((short) 12);
        f2.setFontHeightInPoints((short) 12);
        fTitle.setFontHeightInPoints((short) 12);
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs2.setAlignment(HorizontalAlignment.CENTER);
        cs3.setAlignment(HorizontalAlignment.LEFT);
        csTitle.setAlignment(HorizontalAlignment.LEFT);
        cs.setFont(f);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs2.setFont(f2);
        cs2.setBorderRight(BorderStyle.THIN);
        cs2.setBorderLeft(BorderStyle.THIN);
        cs2.setBorderTop(BorderStyle.THIN);
        cs2.setBorderBottom(BorderStyle.THIN);
        cs3.setBorderRight(BorderStyle.THIN);
        cs3.setBorderLeft(BorderStyle.THIN);
        cs3.setBorderTop(BorderStyle.THIN);
        cs3.setBorderBottom(BorderStyle.THIN);
        csTitle.setFont(fTitle);
        cellTitle = title.createCell(1);
        cellTitle.setCellStyle(csTitle);
        cellTitle.setCellValue("Laporan uang masuk tanggal "+hari.getText());
        cell = header.createCell(0);
        cell.setCellStyle(cs);
        cell.setCellValue("No");
        cell = header.createCell(1);
        cell.setCellStyle(cs);
        cell.setCellValue("Detail");
        cell = header.createCell(2);
        cell.setCellStyle(cs);
        cell.setCellValue("Jenis");
        cell = header.createCell(3);
        cell.setCellStyle(cs);
        cell.setCellValue("Tanggal");
        cell = header.createCell(4);
        cell.setCellStyle(cs);
        cell.setCellValue("Debit");
        cell = header.createCell(5);
        cell.setCellStyle(cs);
        cell.setCellValue("Kredit");
        cell = header.createCell(6);
        cell.setCellStyle(cs);
        cell.setCellValue("Saldo");
        
        sheet.autoSizeColumn(0);
        sheet.setColumnWidth(1, 256*25);
        sheet.setColumnWidth(2, 2160);
        sheet.setColumnWidth(3, 3960);
        sheet.setColumnWidth(4, 3960);
        sheet.setColumnWidth(5, 3960);
        sheet.setColumnWidth(6, 3960);
        
        model.queryLaporanHarian(hari_pilih.getValue().toString(), jenis.getSelectionModel().getSelectedItem().toString());
        int no=1;
        int index=0;
        kon.res=kon.stat.executeQuery(model.queryLoad);
        while(kon.res.next()){
            no++;
            index++;
            String debitFormat=num.format(Integer.parseInt(kon.res.getString(2).toString()));
            String kreditFormat=num.format(Integer.parseInt(kon.res.getString(3).toString()));
            String saldoFormat=num.format(Integer.parseInt(kon.res.getString(4).toString()));
            XSSFRow row = sheet.createRow(no);
            row.createCell(0).setCellValue(no);
            cell=row.createCell(0);
            cell.setCellStyle(cs2);
            cell.setCellValue(index);
            cell=row.createCell(1);
            cell.setCellStyle(cs3);
            cell.setCellValue(kon.res.getString(1));
            cell=row.createCell(2);
            cell.setCellStyle(cs2);
            cell.setCellValue(kon.res.getString(5));
            cell=row.createCell(3);
            cell.setCellStyle(cs2);
            cell.setCellValue(kon.res.getString(6));
            cell=row.createCell(4);
            cell.setCellStyle(cs2);
            cell.setCellValue(debitFormat);
            cell=row.createCell(5);
            cell.setCellStyle(cs2);
            cell.setCellValue(kreditFormat);
            cell=row.createCell(6);
            cell.setCellStyle(cs2);
            cell.setCellValue(saldoFormat);
        }
        model.queryLaporanHarianTotal(hari_pilih.getValue().toString(), jenis.getSelectionModel().getSelectedItem().toString());
        kon.res=kon.stat.executeQuery(model.queryLoad);
        while(kon.res.next()){
            no++;
            XSSFRow row = sheet.createRow(no);
            cell=row.createCell(3);
            cell.setCellStyle(cs);
            cell.setCellValue("Total");
            if(kon.res.getString(1)==null){
                cell=row.createCell(4);
                cell.setCellStyle(cs);
                cell.setCellValue("0");
            }
            else{
                cell=row.createCell(4);
                cell.setCellStyle(cs);
                cell.setCellValue(num.format(Integer.parseInt(kon.res.getString(1))));
            }
                
            if(kon.res.getString(2)==null){
                cell=row.createCell(5);
                cell.setCellStyle(cs);
                cell.setCellValue("0");
            }
            else{
                cell=row.createCell(5);
                cell.setCellStyle(cs);
                cell.setCellValue(num.format(Integer.parseInt(kon.res.getString(2))));
            }
            
            if(kon.res.getString(3)==null){
                cell=row.createCell(6);
                cell.setCellStyle(cs);
                cell.setCellValue("0");
            }
            else{
                cell=row.createCell(6);
                cell.setCellStyle(cs);
                cell.setCellValue(num.format(Integer.parseInt(kon.res.getString(3))));
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to Excel");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Microsoft Office Excel 2010", "*.xlsx"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            TrayNotification tray = new TrayNotification();
            FileOutputStream fileout = new FileOutputStream(selectedFile.getAbsoluteFile());
            wb.write(fileout);
            fileout.close();
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Save Success");
            tray.setMessage("File berhasil disimpan...");
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.millis(1000));
            tray.setRectangleFill(Color.valueOf("#4183D7"));
            tray.setImage(new Image("/img/icons8_Ok_96px.png"));
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setHari();
        setJenis();
        setStyleTable();
        kon.db();
        num.setMaximumFractionDigits(3);
        loadTable();
    }    
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(hari_pilih.getValue()));
        hari.setText(dateText);
    }
    
    @FXML
    private void refreshClicked(ActionEvent event){
        loadTable();
    }
    
}
