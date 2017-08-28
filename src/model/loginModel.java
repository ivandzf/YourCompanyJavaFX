package model;

import db.koneksi;
import javax.swing.JOptionPane;

public class loginModel {
    
    koneksi kon = new koneksi();
    private boolean status_login=false;
    private String id;
    private String status;
    private String level;
    private String nama;
    
    public void setLogin(String username, String password){
        try {
            int row=0;
            kon.db();
            kon.res=kon.stat.executeQuery("select id, level, nama, status from login where username='"+username+"' and password='"+password+"'");
            while(kon.res.next()){
                row=kon.res.getRow();
                this.id=kon.res.getString("id");
                this.level=kon.res.getString("level");
                this.nama=kon.res.getString("nama");
                this.status=kon.res.getString("status");
            }
            if(row==1){
                status_login=true;
            }
            else{
                status_login=false;
            }
        } catch (Exception e) {
        }
    }
    
    public void setStatus(String id){
        try {
            kon.db();
            kon.stat.executeUpdate("update login set status ='Login' where id='"+id+"'");
        } catch (Exception e) {
        }
        
        
    }
    
    public boolean getLogin(){
        return status_login;
    }
    
    public String getId(){
        return id;
    }
    
    public String getLevel(){
        return level;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getStatus(){
        return status;
    }
    
}
