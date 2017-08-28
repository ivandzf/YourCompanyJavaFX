package controller;

import db.koneksi;
import function.time;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import model.dashboardModel;

public class DashboardController implements Initializable {

    @FXML
    private TextField tahun;
    
    @FXML
    private LineChart<String, Number> LineChartMasuk;
    
    @FXML
    private LineChart<String, Number> LineChartKeluar;
    
    koneksi kon = new koneksi();
    dashboardModel model = new dashboardModel();
    time time = new time();
    NumberFormat num = NumberFormat.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        num.setMaximumFractionDigits(3);
        tahun.setText(time.tanggalTahun());
        setChart();
    }
    
    @FXML
    private void refreshClicked(){
        setChart();
    }

    private void setChart(){
        kon.db();
        LineChartKeluar.getData().clear();
        LineChartMasuk.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series();
        XYChart.Series<String, Number> series2 = new XYChart.Series();
        try {
            model.setChartMasuk(tahun.getText());
            kon.res=kon.stat.executeQuery(model.getChartMasuk());
            while(kon.res.next()){
                series.getData().add(new XYChart.Data(kon.res.getString(2), Integer.parseInt(kon.res.getString(1))));
            }
            model.setChartKeluar(tahun.getText());
            kon.res=kon.stat.executeQuery(model.getChartKeluar());
            while(kon.res.next()){
                series2.getData().add(new XYChart.Data(kon.res.getString(2), Integer.parseInt(kon.res.getString(1))));
            }
            
        } catch (Exception e) {
        }
        series.setName("Uang Masuk");
        series2.setName("Uang Keluar");
        
        LineChartMasuk.getData().addAll(series);
        LineChartKeluar.getData().addAll(series2);
        
        for (final XYChart.Data<String,Number> data: series.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    Tooltip tool = new Tooltip();
                    tool.setFont(new Font("Segoe UI", 24));
                    tool.autoFixProperty();
                    tool.install(data.getNode(), new Tooltip(String.valueOf(num.format(data.getYValue()))));
                    
                }
            });
        }
        for (final XYChart.Data<String,Number> data: series2.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    Tooltip.install(data.getNode(), new Tooltip(String.valueOf(num.format(data.getYValue()))));
                }
            });
        }
    }
    
}
