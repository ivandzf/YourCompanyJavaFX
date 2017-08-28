package function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Label;
import javax.swing.Timer;

public class time {
    

    public String tanggal(){
        Date tgl = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return(sdf.format(tgl));       
    }
    public String tanggalQuery(){
        Date tgl = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return(sdf.format(tgl));       
    }
    public String tanggalBulan(){
        Date tgl = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return(sdf.format(tgl));       
    }
    public String tanggalTahun(){
        Date tgl = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return(sdf.format(tgl));       
    }
    
    
    
}
