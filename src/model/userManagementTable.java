package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class userManagementTable {
    private final StringProperty id;
    private final StringProperty no;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty nama;
    private final StringProperty email;
    private final StringProperty level;
    private final StringProperty status;
    
    public userManagementTable(String id,String no,String username,String password
    ,String nama,String email,String level,String status){
        this.id=new SimpleStringProperty(id);
        this.no=new SimpleStringProperty(no);
        this.username=new SimpleStringProperty(username);
        this.password=new SimpleStringProperty(password);
        this.nama=new SimpleStringProperty(nama);
        this.email=new SimpleStringProperty(email);
        this.level=new SimpleStringProperty(level);
        this.status=new SimpleStringProperty(status);
    }
    
    public String getId(){
        return id.get();
    }
    
    public String getNo(){
        return no.get();
    }
    
    public String getUsername(){
        return username.get();
    }
    
    public String getPassword(){
        return password.get();
    }
    
    public String getNama(){
        return nama.get();
    }
    
    public String getEmail(){
        return email.get();
    }
    
    public String getLevel(){
        return level.get();
    }
    
    public String getStatus(){
        return status.get();
    }
    
    public void setId(String value){
        id.set(value);
    }
    
    public void setNo(String value){
        no.set(value);
    }
    
    public void setUsername(String value){
        username.set(value);
    }
    
    public void setPassword(String value){
        password.set(value);
    }
    
    public void setNama(String value){
        nama.set(value);
    }
    
    public void setEmail(String value){
        email.set(value);
    }
    
    public void setLevel(String value){
        level.set(value);
    }
    
    public void setStatus(String value){
        status.set(value);
    }
    
    public StringProperty idProperty(){
        return id;
    }
    
    public StringProperty noProperty(){
        return no;
    }
    
    public StringProperty usernameProperty(){
        return username;
    }
    
    public StringProperty passwordProperty(){
        return password;
    }
    
    public StringProperty namaProperty(){
        return nama;
    }
    
    public StringProperty emailProperty(){
        return email;
    }
    
    public StringProperty levelProperty(){
        return level;
    }
    
    public StringProperty statusProperty(){
        return status;
    }
    
}
