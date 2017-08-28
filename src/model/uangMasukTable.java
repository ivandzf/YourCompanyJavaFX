package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class uangMasukTable {

    private final StringProperty id;
    private final StringProperty no;
    private final StringProperty detail;
    private final StringProperty debit;
    private final StringProperty debitBiasa;
    private final StringProperty kredit;
    private final StringProperty kreditBiasa;
    private final StringProperty saldo;
    private final StringProperty pilih;
    private final StringProperty tanggal;
    private final StringProperty tanggalBiasa;
    private final StringProperty waktuInput;
    
    public uangMasukTable(String id,String no,String detail, String pilih, 
            String tanggal, String debit, String kredit, String saldo, String waktuInput,
            String debitBiasa, String kreditBiasa, String tanggalBiasa){
        this.id=new SimpleStringProperty(id);
        this.no=new SimpleStringProperty(no);
        this.detail=new SimpleStringProperty(detail);
        this.debit=new SimpleStringProperty(debit);
        this.debitBiasa=new SimpleStringProperty(debitBiasa);
        this.kredit=new SimpleStringProperty(kredit);
        this.kreditBiasa=new SimpleStringProperty(kreditBiasa);
        this.saldo=new SimpleStringProperty(saldo);
        this.pilih=new SimpleStringProperty(pilih);
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
    
    public String getDebit(){
        return debit.get();
    }
    
    public String getDebitBiasa(){
        return debitBiasa.get();
    }
    
    public String getKredit(){
        return kredit.get();
    }
    
    public String getKreditBiasa(){
        return kreditBiasa.get();
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
    
    public String getTanggalBiasa(){
        return tanggalBiasa.get();
    }
    
    public String getWaktuInput(){
        return waktuInput.get();
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
    
    public void setKredit(String value){
        kredit.set(value);
    }
    
    public void setKreditBiasa(String value){
        kreditBiasa.set(value);
    }
    
    public void setDebit(String value){
        debit.set(value);
    }
    
    public void setDebitBiasa(String value){
        debitBiasa.set(value);
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
    
    public void setTanggalBiasa(String value){
        tanggalBiasa.set(value);
    }
    
    public void setWaktuInput(String value){
        waktuInput.set(value);
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
    
    public StringProperty debitProperty(){
        return debit;
    }
    
    public StringProperty debitBiasaProperty(){
        return debitBiasa;
    }
    public StringProperty kreditProperty(){
        return kredit;
    }
    public StringProperty kreditBiasaProperty(){
        return kreditBiasa;
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
    
    public StringProperty tanggalBiasaProperty(){
        return tanggalBiasa;
    }
    
    public StringProperty waktuInputProperty(){
        return waktuInput;
    }
    
    
}
