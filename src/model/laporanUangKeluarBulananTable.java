package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class laporanUangKeluarBulananTable {

    private final StringProperty no;
    private final StringProperty tanggal;
    private final StringProperty uang;
    
    public laporanUangKeluarBulananTable(String no, String tanggal, String uang){
        this.no=new SimpleStringProperty(no);
        this.tanggal=new SimpleStringProperty(tanggal);
        this.uang=new SimpleStringProperty(uang);
    }
    
    public String getNo(){
        return no.get();
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
    
    public void setUang(String value){
        uang.set(value);
    }
    
    public void setTanggal(String value){
        tanggal.set(value);
    }
    
    public StringProperty noProperty(){
        return no;
    }
    
    public StringProperty uangProperty(){
        return uang;
    }
    
    public StringProperty tanggalProperty(){
        return tanggal;
    }
    
}
