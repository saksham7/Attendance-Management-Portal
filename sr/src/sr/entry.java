
package sr;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.json.JSONArray;
import org.json.JSONException;
import static sr.JsonREader.readJsonFromUrl;

public class entry extends JFrame {
    JButton btn1,btn2,btn3;
    JLabel lbl;
    public entry(String dept,String year,String batch,String date,String a){
        setLayout(null);
        btn1=new JButton("Submit Attendence");
        btn2=new JButton("View Attendence");
        btn3=new JButton("EXIT");
        lbl=new JLabel(new ImageIcon("C:\\Users\\Saksham's PC\\Documents\\NetBeansProjects\\sr\\src\\images\\logo2.jpg"));
        add(lbl);
        add(btn1);
        add(btn2);
        add(btn3);
        lbl.setBounds(4,0,300,100);
        btn1.setBounds(10,80,280,60);
        btn2.setBounds(10,150,280,60);
        btn3.setBounds(10,220,280,60);
        btn1.setBackground(Color.white);
        btn2.setBackground(Color.white);
        btn3.setBackground(Color.white);
        
        btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JSONArray json = readJsonFromUrl("http://localhost/sr/check.php?date="+date+"&reg="+a);
                    String res=json.get(0).toString();
                    int count=Integer.parseInt(json.get(1).toString());
                    if(res.equals("Already Submitted")){
                        new attResult(count);
                    }
                    else
                    new attTable(dept,year,batch,date,a);  
                } catch (IOException ex) {
                    Logger.getLogger(entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DatePickerExample(a);
                } catch (JSONException ex) {
                    Logger.getLogger(entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        btn3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });
        setBounds(550,250,300,300);
        getContentPane().setBackground(Color.gray);
        setUndecorated(true);
       
        setVisible(true);
    }
   
}
