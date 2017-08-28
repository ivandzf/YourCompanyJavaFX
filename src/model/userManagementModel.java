package model;

import db.koneksi;

public class userManagementModel {
    
    koneksi kon = new koneksi();
    public String queryLoad="select * from login where level !='Admin'";
    private int rowUsername=0;
    private boolean statusInsert=false;
    private boolean statusUpdate=false;
    private boolean statusDelete=false;
    
    public void insertUserManagement(String username, String password, String nama, String email, String level){
        try {
            kon.db();
            kon.stat.executeUpdate("insert into login values (null,'"
                    +username+"','"+password+"','"+nama+"','"
                    +email+"','"+level+"','Tidak Login')");
            statusInsert=true;
        } catch (Exception e) {
            statusInsert=false;
            System.out.println(e);
        }
    }
    
    public void cekUsername(String username){
        try {
            kon.db();
            kon.res=kon.stat.executeQuery("select username from login where username='"+username+"'");
            while(kon.res.next()){
                rowUsername=kon.res.getRow();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateUserManagement(String id, String password, String nama, String email, String level, String status){
        try {
            kon.db();
            kon.stat.executeUpdate("update login set password='"+password
                    +"', nama='"+nama+"', email='"+email+"', level='"+level
                    +"', status='"+status+"' where id='"+id+"'");
            statusUpdate=true;
        } catch (Exception e) {
            statusUpdate=false;
            System.out.println(e);
        }
    }
    
    public void deleteUserManagement(String id){
        try {
            kon.db();
            kon.stat.executeUpdate("delete from login where id='"+id+"'");
            statusDelete=true;
        } catch (Exception e) {
            statusDelete=false;
            System.out.println(e);
        }
    }
    
    public boolean getStatusInsert(){
        return statusInsert;
    }
    
    public int getRowUsername(){
        return rowUsername;
    }
    
    public boolean getStatusUpdate(){
        return statusUpdate;
    }
    
    public boolean getStatusDelete(){
        return statusDelete;
    }
    
    
}
