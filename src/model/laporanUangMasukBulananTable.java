package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class laporanUangMasukBulananTable {
    
    private final StringProperty no;
    private final StringProperty tanggal;
    private final StringProperty uangBakul;
    private final StringProperty uangHotel;
    private final StringProperty uang;
    
    public laporanUangMasukBulananTable(String no, String tanggal, String uangBakul, String uangHotel, String uang){
        this.no=new SimpleStringProperty(no);
        this.tanggal=new SimpleStringProperty(tanggal);
        this.uangBakul=new SimpleStringProperty(uangBakul);
        this.uangHotel=new SimpleStringProperty(uangHotel);
        this.uang=new SimpleStringProperty(uang);
    }
    
    public String getNo(){
        return no.get();
    }
    
    public String getUangBakul(){
        return uangBakul.get();
    }
    public String getUangHotel(){
        return uangHotel.get();
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
    
    public void setUangBakul(String value){
        uangBakul.set(value);
    }
    public void setUangHotel(String value){
        uangHotel.set(value);
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
    
    public StringProperty uangBakulProperty(){
        return uangBakul;
    }
    public StringProperty uangHotelProperty(){
        return uangHotel;
    }
    public StringProperty uangProperty(){
        return uang;
    }
    
    public StringProperty tanggalProperty(){
        return tanggal;
    }
    
}
