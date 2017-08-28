package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class laporanUangKeluarHarianTable {
    
    private final StringProperty no;
    private final StringProperty detail;
    private final StringProperty uang;
    private final StringProperty tanggal;
    
    public laporanUangKeluarHarianTable(String no,String detail, String uang, String tanggal){
        this.no=new SimpleStringProperty(no);
        this.detail=new SimpleStringProperty(detail);
        this.uang=new SimpleStringProperty(uang);
        this.tanggal=new SimpleStringProperty(tanggal);
    }
    
    public String getNo(){
        return no.get();
    }
    
    public String getDetail(){
        return detail.get();
    }
    
    public String getUang(){
        return uang.get();
    }
    
    public String getTanggal(){
        return tanggal.get();
    }
    
    public void setNo(String value){
        no.set(value);
    }
    
    public void setDetail(String value){
        detail.set(value);
    }
    
    public void setUang(String value){
        uang.set(value);
    }
    
    public void setTanggal(String value){
        tanggal.set(value);
    }
    
    public StringProperty noProperty(){
        return no;
    }
    
    public StringProperty detailProperty(){
        return detail;
    }
    
    public StringProperty uangProperty(){
        return uang;
    }
    
    public StringProperty tanggalProperty(){
        return tanggal;
    }
    
}
