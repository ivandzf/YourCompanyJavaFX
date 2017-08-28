package db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class koneksi {
    public Connection con;
    public ResultSet res;
    public Statement stat;
    
    public void db() {
    Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("setting.properties"));
            String driverName = properties.getProperty("driverName");
            String jdbcUrl = properties.getProperty("jdbcUrl");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String serverName = properties.getProperty("serverName");
            String port = properties.getProperty("port");
            String databaseName = properties.getProperty("databaseName");
            try {
                Class.forName(driverName);
                con=DriverManager.getConnection(jdbcUrl+serverName+":"+port+"/"+databaseName,user,password);
                stat=con.createStatement();
            } catch (Exception e) {
                
            }
            
            
        } catch (IOException e) {
             
        }
    
    }
    
    public void testKoneksi(String server, String port, String database, String username, String password){
            if ("".equals(server)||"".equals(port)||"".equals(database))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setHeaderText(null);
                alert.setContentText("Mohon lengkapi server, port dan database !!");
                alert.showAndWait();
            }
            else{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database,username,password);
                stat=con.createStatement();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukses");
                alert.setHeaderText(null);
                alert.setContentText("Koneksi berhasil tersambung !!");
                alert.showAndWait();
            } catch (Throwable e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(String.valueOf(e.getMessage()));
                alert.showAndWait();
            }
            }
    }
    public void simpanKoneksi(String server, String port, String database, String username, String password){
            if ("".equals(server)||"".equals(port)||"".equals(database))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setHeaderText(null);
                alert.setContentText("Mohon lengkapi server, port dan database !!");
                alert.showAndWait();
            }
            else{
            try {
                Properties properties = new Properties();
                properties.setProperty("driverName", "com.mysql.jdbc.Driver");
                properties.setProperty("jdbcUrl", "jdbc:mysql://");
                properties.setProperty("user", username);
                properties.setProperty("password", password);
                properties.setProperty("serverName", server);
                properties.setProperty("port", port);
                properties.setProperty("databaseName", database);

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database,username,password);
                    stat=con.createStatement();
                    properties.store(new FileOutputStream("setting.properties"), "Setting Koneksi MySQL");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sukses");
                    alert.setHeaderText(null);
                    alert.setContentText("Koneksi berhasil disimpan...");
                    alert.showAndWait();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(String.valueOf(ex));
                    alert.showAndWait();
                }
                
            } catch (Throwable e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(String.valueOf(e.getMessage()));
                alert.showAndWait();
            }
            }
    }
    
}
