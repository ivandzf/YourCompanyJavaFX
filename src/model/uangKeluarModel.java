package model;

import db.koneksi;
import java.sql.SQLException;

public class uangKeluarModel {

    private String query="select id,detail,uang_keluar, "
            + "DATE_FORMAT(tanggal,'%d %M %Y') as tgl, tanggal,DATE_FORMAT(waktu_input,'%d %M %Y %T') from uang_keluar ";
    private String filter;
    private String order=" order by tgl asc, detail asc";
    private String detailCari;
    public String queryLoad="";
    
    private String queryLaporanHarian="select detail,uang_keluar,"
            + "DATE_FORMAT(tanggal,'%d %M %Y') as tgl from uang_keluar ";
    private String queryLaporanTotal="select sum(uang_keluar)"
            + " from uang_keluar ";
    
    private String queryLaporanBulanan="select DATE_FORMAT(tanggal,'%d %M %Y') as tgl,"
            + "sum(uang_keluar) from uang_keluar ";
    
    private String queryFilterBulanan=" group by tgl order by tgl asc";
    
    koneksi kon = new koneksi();
    private boolean statusInsert = false;
    private boolean statusUpdate = false;
    private boolean statusDelete = false;
    
    public void filterHari(String hari, String cari){
        filter=" where tanggal='"+hari+"'";
        detailCari=" and detail like '%"+cari+"%'";
        queryLoad=query+filter+detailCari+order;
    }
    
    public void filterBulan(String bulan, String tahun, String cari){
        filter=" where DATE_FORMAT(tanggal,'%M/%Y')='"+bulan+"/"+tahun+"'";
        detailCari=" and detail like '%"+cari+"%'";
        queryLoad=query+filter+detailCari+order;
    }
    
    public void filterSemua(String cari){
        detailCari=" where detail like '%"+cari+"%'";
        queryLoad=query+detailCari+order;
    }
    
    public void filterLaporanHarian(String date){
        filter=" where tanggal='"+date+"'";
        queryLoad=queryLaporanHarian+filter+order;
    }
    
    public void filterLaporanHarianTotal(String date){
        filter=" where tanggal='"+date+"'";
        queryLoad=queryLaporanTotal+filter;
    }
    
    public void filterLaporanBulanan(String bulan, String tahun){
        filter=" where DATE_FORMAT(tanggal,'%M/%Y')='"+bulan+"/"+tahun+"'";
        queryLoad=queryLaporanBulanan+filter+queryFilterBulanan;
    }
    
    public void filterLaporanBulananTotal(String bulan, String tahun){
        filter=" where DATE_FORMAT(tanggal,'%M/%Y')='"+bulan+"/"+tahun+"'";
        queryLoad=queryLaporanTotal+filter;
    }
    
    public void insertUangKeluar(String detail, String uang, String date, String waktu_input) {
        try {
            kon.db();
            kon.stat.executeUpdate("insert into uang_keluar values (null,'"
                +detail+"','"+uang+"','"+date+"','"+waktu_input+"')");
            statusInsert=true;
        } catch (SQLException e) {
            statusInsert=false;
        }
    }
    
    public boolean getStatusInert(){
        return statusInsert;
    }
    
    public void updateUangKeluar(String id, String detail, String uang, String date){
        try {
            kon.db();
            kon.stat.executeUpdate("update uang_keluar set detail='"+detail+
                    "',uang_keluar='"+uang+
                    "',tanggal='"+date+"' where id='"+id+"'");
            statusUpdate=true;
        } catch (Exception e) {
            statusUpdate=false;
        }
    }
    
    public boolean getStatusUpdate(){
        return statusUpdate;
    }
    
    public void deleteUangKeluar(String id){
        try {
            kon.db();
            kon.stat.executeUpdate("delete from uang_keluar where id='"+id+"'");
            statusDelete=true;
        } catch (Exception e) {
            statusDelete=false;
        }
    }
    
    public boolean getStatusDelete(){
        return statusDelete;
    }
    
}
