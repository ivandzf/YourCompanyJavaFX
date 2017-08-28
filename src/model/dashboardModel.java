package model;

import db.koneksi;

public class dashboardModel {

private String query;

    public void setChartKeluar(String tahun){
        query="select sum(uang_keluar), date_format(tanggal,'%M') from uang_keluar where date_format(tanggal,'%Y')='"+tahun+"' group by date_format(tanggal,'%M') order by tanggal";
    }
    
    public String getChartKeluar(){
        return query;
    }
    
    public void setChartMasuk(String tahun){
        query="select sum(kredit), date_format(tanggal,'%M') from uang_masuk where date_format(tanggal,'%Y')='"+tahun+"' group by date_format(tanggal,'%M') order by tanggal";
    }
    
    public String getChartMasuk(){
        return query;
    }
    
}
