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
import javafx.util.Duration;
import javax.swing.JOptionPane;
import model.laporanUangKeluarHarianTable;
import model.uangKeluarModel;
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

public class LaporanUangKeluarHarianController implements Initializable {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    
    @FXML
    private TableView<laporanUangKeluarHarianTable> tableUangKeluar;
    
    @FXML
    private TableColumn<laporanUangKeluarHarianTable, String> columnNo;
    
    @FXML
    private TableColumn<laporanUangKeluarHarianTable, String> columnDetail;
    
    @FXML
    private TableColumn<laporanUangKeluarHarianTable, String> columnUang;
        
    @FXML
    private TableColumn<laporanUangKeluarHarianTable, String> columnTanggal;
    
    private ObservableList<laporanUangKeluarHarianTable> data;
    
    @FXML
    private TextField hari;
    
    @FXML
    private DatePicker hari_pilih;
    
    @FXML
    private Label total_uang_keluar;
    
    @FXML
    private Button btnOutput;
    
    @FXML
    private ContextMenu contextOutput;
    
    koneksi kon = new koneksi();
    uangKeluarModel model = new uangKeluarModel();
    NumberFormat num = NumberFormat.getInstance();
    time time = new time();
    navigation nav = new navigation();
    
    private void setHari(){
        hari.setText(time.tanggal());
        hari_pilih.setValue(LocalDate.parse(time.tanggalQuery()));
    }
    
    private void setStyleTable(){
        columnNo.setStyle("-fx-alignment: CENTER");
        columnUang.setStyle("-fx-alignment: CENTER");
        columnTanggal.setStyle("-fx-alignment: CENTER");
    }
    
    private void loadTable(){
        try {
            nav.animationFade(tableUangKeluar);
            model.filterLaporanHarian(hari_pilih.getValue().toString());
            int no=0;
            data=FXCollections.observableArrayList();
            kon.res=kon.stat.executeQuery(model.queryLoad);
            while(kon.res.next()){
                no++;
                String uangFormat=num.format(Integer.parseInt(kon.res.getString(2).toString()));
                data.add(new laporanUangKeluarHarianTable(String.valueOf(no),
                        kon.res.getString(1), uangFormat, kon.res.getString(3)));
            }
            columnNo.setCellValueFactory(new PropertyValueFactory<>("no"));
            columnDetail.setCellValueFactory(new PropertyValueFactory<>("detail"));
            columnUang.setCellValueFactory(new PropertyValueFactory<>("uang"));
            columnTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
            tableUangKeluar.setItems(null);
            tableUangKeluar.setItems(data);
            model.filterLaporanHarianTotal(hari_pilih.getValue().toString());
            kon.res=kon.stat.executeQuery(model.queryLoad);
            while(kon.res.next()){
                if(kon.res.getString(1)==null){
                    total_uang_keluar.setText("-");
                }
                else{
                    total_uang_keluar.setText(num.format(Integer.parseInt(kon.res.getString(1).toString())));
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
                Class.forName(driverName);
                File report = new File("src/report/ReportUangKeluarHarian.jrxml");
                jasDes = JRXmlLoader.load(report);
                HashMap parameter = new HashMap();
                parameter.put("tanggal_query", hari_pilih.getValue().toString());
                parameter.put("tanggal_text", hari.getText());
                parameter.put("total", total_uang_keluar.getText());
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
        cellTitle.setCellValue("Laporan uang keluar tanggal "+hari.getText());
        cell = header.createCell(0);
        cell.setCellStyle(cs);
        cell.setCellValue("No");
        cell = header.createCell(1);
        cell.setCellStyle(cs);
        cell.setCellValue("Detail");
        cell = header.createCell(2);
        cell.setCellStyle(cs);
        cell.setCellValue("Tanggal");
        cell = header.createCell(3);
        cell.setCellStyle(cs);
        cell.setCellValue("Uang Keluar");
        
        sheet.autoSizeColumn(0);
        sheet.setColumnWidth(1, 256*25);
        sheet.setColumnWidth(2, 4320);
        sheet.setColumnWidth(3, 5760);
        
        model.filterLaporanHarian(hari_pilih.getValue().toString());
        int no=1;
        int index=0;
        kon.res=kon.stat.executeQuery(model.queryLoad);
        while(kon.res.next()){
            no++;
            index=0;
            String uangKeluar=num.format(Integer.parseInt(kon.res.getString(2).toString()));
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
            cell.setCellValue(kon.res.getString(3));
            cell=row.createCell(3);
            cell.setCellStyle(cs2);
            cell.setCellValue(uangKeluar);
        }
        model.filterLaporanHarianTotal(hari_pilih.getValue().toString());
        kon.res=kon.stat.executeQuery(model.queryLoad);
        while(kon.res.next()){
            no++;
            XSSFRow row = sheet.createRow(no);
            cell=row.createCell(2);
            cell.setCellStyle(cs);
            cell.setCellValue("Total");
            if(kon.res.getString(1)==null){
                cell=row.createCell(3);
                cell.setCellStyle(cs);
                cell.setCellValue("0");
            }
            else{
                cell=row.createCell(3);
                cell.setCellStyle(cs);
                cell.setCellValue(num.format(Integer.parseInt(kon.res.getString(1))));
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to Excel");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Microsoft Office Excel 2010", "*.xlsx"));
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
    
    
    @FXML
    private void refreshClicked(){
        loadTable();
    }
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(hari_pilih.getValue()));
        hari.setText(dateText);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setHari();
        setStyleTable();
        kon.db();
        num.setMaximumFractionDigits(3);
        loadTable();
    }    
    
}
