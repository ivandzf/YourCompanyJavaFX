package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class uangKeluarTable {
    
    private final StringProperty id;
    private final StringProperty no;
    private final StringProperty detail;
    private final StringProperty uang;
    private final StringProperty tanggal;
    private final StringProperty waktuInput;
    private final StringProperty uangBiasa;
    private final StringProperty tanggalBiasa;
    
    public uangKeluarTable(String id,String no,String detail, String uang, 
            String tanggal, String waktuInput, 
            String uangBiasa, String tanggalBiasa){
        this.id=new SimpleStringProperty(id);
        this.no=new SimpleStringProperty(no);
        this.detail=new SimpleStringProperty(detail);
        this.uang=new SimpleStringProperty(uang);
        this.uangBiasa=new SimpleStringProperty(uangBiasa);
        this.tanggal=new SimpleStringProperty(tanggal);
        this.tanggalBiasa=new SimpleStringProperty(tanggalBiasa);
        this.waktuInput=new SimpleStringProperty(waktuInput);
    }
    
    public String getId(){
        return id.get();
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
    
    public String getWaktuInput(){
        return waktuInput.get();
    }
    
    public String getUangBiasa(){
        return uangBiasa.get();
    }
    
    public String getTanggalBiasa(){
        return tanggalBiasa.get();
    }
    
    public void setId(String Value){
        id.set(Value);
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
    
    public void setWaktuInput(String value){
        waktuInput.set(value);
    }
    
    public void setUangBiasa(String value){
        uangBiasa.set(value);
    }
    
    public void setTanggalBiasa(String value){
        tanggalBiasa.set(value);
    }
    
    public StringProperty idProperty(){
        return id;
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
    
    public StringProperty waktuInputProperty(){
        return waktuInput;
    }
    
    public StringProperty uangBiasaProperty(){
        return uangBiasa;
    }
    
    public StringProperty tanggalBiasaProperty(){
        return tanggalBiasa;
    }
    
}
