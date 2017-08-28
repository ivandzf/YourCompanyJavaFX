package model;

import db.koneksi;

public class homeModel {

    private String id, username, password, nama, email, level;
    koneksi kon = new koneksi();
    
    public void setValue(String id){
        try {
            kon.db();
            kon.res=kon.stat.executeQuery("select * from login where id='"+id+"'");
            while(kon.res.next()){
                this.id=kon.res.getString(1);
                this.username=kon.res.getString(2);
                this.password=kon.res.getString(3);
                this.nama=kon.res.getString(4);
                this.email=kon.res.getString(5);
                this.level=kon.res.getString(6);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void setStatus(String id){
        try {
            kon.db();
            kon.stat.executeUpdate("update login set status='Tidak Login' where id='"+id+"'");
        } catch (Exception e) {
        }
    }
    
    public String getId(){
        return id;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getLevel(){
        return level;
    }
    
}
