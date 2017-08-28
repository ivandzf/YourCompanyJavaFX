package model;

import db.koneksi;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class uangMasukModel {
    
    public String query="select id_uang_masuk,detail,debit,kredit, "
            + "jenis, DATE_FORMAT(tanggal,'%d %M %Y') as tgl, tanggal,DATE_FORMAT(waktu_input,'%d %M %Y %T'),(debit-kredit) from uang_masuk ";
    private String filter;
    private String order=" order by tgl asc, detail asc";
    private String orderDetail=" order by detail asc";
    private String detailCari;
    private String jenisCari;
    public String queryLoad="";
    
    private String queryLaporanHarian="select detail,debit,kredit,(debit-kredit),jenis,"
            + "DATE_FORMAT(tanggal,'%d %M %Y') as tgl from uang_masuk ";    
    
    private String queryLaporanTotalHarian="select sum(debit), sum(kredit), sum(debit-kredit) from uang_masuk ";    
    private String queryLaporanTotalBulanan="select sum(case when jenis='Bakul' then kredit else 0 end),"
            + "sum(case when jenis='Hotel' then kredit else 0 end),sum(kredit) as um from uang_masuk ";
    private String queryLaporanBulanan="select sum(case when jenis='Bakul' then kredit else 0 end),"
            + "sum(case when jenis='Hotel' then kredit else 0 end), sum(kredit),"
            + " DATE_FORMAT(tanggal,'%d %M %Y')"
            + " as tgl from uang_masuk ";
    
    private String queryFilterBulanan=" group by tgl order by tgl ";
    
    
    koneksi kon = new koneksi();
    private boolean statusInsert=false;
    private boolean statusUpdate=false;
    private boolean statusDelete=false;
    
    public void filterHari(String hari, String cari, String jenis){
        if(jenis.equals("Semua")){
            jenisCari="";
        }
        else{
            jenisCari=" and jenis='"+jenis+"'";
        }
        filter=" where tanggal='"+hari+"'";
        detailCari=" and detail like '%"+cari+"%'";
        queryLoad=query+filter+detailCari+jenisCari+order;
    }
    
    public void filterBulan(String bulan, String tahun, String cari, String jenis){
        if(jenis.equals("Semua")){
            jenisCari="";
        }
        else{
            jenisCari=" and jenis='"+jenis+"'";
        }
        filter=" where DATE_FORMAT(tanggal,'%M/%Y')='"+bulan+"/"+tahun+"'";
        detailCari=" and detail like '%"+cari+"%'";
        queryLoad=query+filter+detailCari+jenisCari+order;
    }
    
    public void filterSemua(String cari, String jenis){
        if(jenis.equals("Semua")){
            jenisCari="";
        }
        else{
            jenisCari=" and jenis='"+jenis+"'";
        }
        detailCari=" where detail like '%"+cari+"%'";
        queryLoad=query+detailCari+jenisCari+order;
    }    
    
    public void queryLaporanHarian(String hari, String jenis){
        if(jenis.equals("Semua")){
            jenisCari="";
        }
        else{
            jenisCari=" and jenis='"+jenis+"'";
        }
        filter=" where tanggal='"+hari+"'";
        queryLoad=queryLaporanHarian+filter+jenisCari+orderDetail;
    }
    public void queryLaporanHarianTotal(String hari, String jenis){
        if(jenis.equals("Semua")){
            jenisCari="";
        }
        else{
            jenisCari=" and jenis='"+jenis+"'";
        }
        filter=" where tanggal='"+hari+"'";
        queryLoad=queryLaporanTotalHarian+filter+jenisCari;
    }
    public void queryLaporanBulanan(String bulan, String tahun){
        filter=" where DATE_FORMAT(tanggal,'%M/%Y')='"+bulan+"/"+tahun+"'";
        queryLoad=queryLaporanBulanan+filter+queryFilterBulanan;
    }
    public void queryLaporanBulananTotal(String bulan, String tahun){
        filter=" where DATE_FORMAT(tanggal,'%M/%Y')='"+bulan+"/"+tahun+"'";
        queryLoad=queryLaporanTotalBulanan+filter;
    }
     
    public void insertUangMasuk(String detail, String debit, String kredit, String jenis, String tanggal, String waktu){
        try {
            kon.db();
            kon.stat.executeUpdate("insert into uang_masuk values "
                + "(null,'"+detail+"','"+debit+"','"+kredit
                +"','"+jenis+ "','"+tanggal+"','"+waktu+"')");
            statusInsert=true;
        } catch (Exception e) {
            statusInsert=false;
        }
    }
     
    public boolean getInsert(){
        return statusInsert;
    }
    
    public void updateUangMasuk(String id, String detail, String debit, String kredit, String jenis, String tanggal){
        try {
            kon.db();
            kon.stat.executeUpdate("update uang_masuk set detail='"
                +detail+"', debit='"+debit+"', kredit='"+kredit
                    +"', jenis='"+jenis+"',tanggal='"+tanggal+"' "
                    + "where id_uang_masuk='"+id+"'");
            statusUpdate=true;
        } catch (Exception e) {
            statusUpdate=false;
        }
    }
     
    public boolean getUpdate(){
        return statusUpdate;
    }
        
    public void deleteUangMasuk(String id){
        try {
            kon.db();
            kon.stat.executeUpdate("delete from uang_masuk where id_uang_masuk='"+id+"'");
            statusDelete=true;
        } catch (Exception e) {
            statusDelete=false;
        }
    }
     
    public boolean getDelete(){
        return statusDelete;
    }
    
}
