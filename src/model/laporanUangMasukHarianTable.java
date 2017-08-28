package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class laporanUangMasukHarianTable {
    
    private final StringProperty no;
    private final StringProperty detail;
    private final StringProperty debit;
    private final StringProperty kredit;
    private final StringProperty saldo;
    private final StringProperty pilih;
    private final StringProperty tanggal;
     
    public laporanUangMasukHarianTable(String no,String detail, String debit,
            String kredit, String saldo, String pilih, String tanggal){
        this.no=new SimpleStringProperty(no);
        this.detail=new SimpleStringProperty(detail);
        this.debit=new SimpleStringProperty(debit);
        this.kredit=new SimpleStringProperty(kredit);
        this.saldo=new SimpleStringProperty(saldo);
        this.pilih=new SimpleStringProperty(pilih);
        this.tanggal=new SimpleStringProperty(tanggal);
    }
    
    public String getNo(){
        return no.get();
    }
    
    public String getDetail(){
        return detail.get();
    }
    
    public String getDebit(){
        return debit.get();
    }
    
    public String getKredit(){
        return kredit.get();
    }
    
    public String getSaldo(){
        return saldo.get();
    }
    
    public String getPilih(){
        return pilih.get();
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
    
    public void setDebit(String value){
        debit.set(value);
    }
    
    public void setKredit(String value){
        kredit.set(value);
    }
    
    public void setSaldo(String value){
        saldo.set(value);
    }      
    
    public void setPilih(String value){
        pilih.set(value);
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
    
    public StringProperty debitProperty(){
        return debit;
    }
    public StringProperty kreditProperty(){
        return kredit;
    }
    public StringProperty saldoProperty(){
        return saldo;
    }
    
    public StringProperty pilihProperty(){
        return pilih;
    }
    
    public StringProperty tanggalProperty(){
        return tanggal;
    }
    
}
