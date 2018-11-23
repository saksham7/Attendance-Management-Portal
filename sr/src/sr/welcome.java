
package sr;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.json.JSONException;

public class welcome extends JFrame {
    JLabel lbl1,lbl2;
    JProgressBar pb;
    int i=0,n=0;
    public welcome() throws IOException, JSONException{
    
    setBounds(400,250,500,300);
    setLayout(null);
    //Background Image
    lbl1=new JLabel(new ImageIcon("C:\\Users\\Saksham's PC\\Documents\\NetBeansProjects\\sr\\src\\images\\back1.jpg"));
    lbl1.setBounds(0,0,500,300);
    
    //Text
    lbl2=new JLabel("ATTENDENCE PORTAL");
    add(lbl2);
    lbl2.setForeground(Color.white);
    lbl2.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 21));
    lbl2.setBounds(130,40,400,60);
    add(lbl1);
    
    //JProgressBar
    pb=new JProgressBar(0,2000);    
    pb.setValue(0);    
    pb.setStringPainted(true); 
    pb.setForeground(new Color(204, 51, 0));
    add(pb);   
    pb.setBounds(80,220,340,20);    
    
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setUndecorated(true);
    setVisible(true);
    iterate();
    
       
    
} 
    
void iterate() throws IOException, JSONException  {    
   while(i<=2010){    
      pb.setValue(i);    
      i=i+30;    
      try{
          Thread.sleep(130);
         } 
      catch(Exception e){
          System.out.println(e.getMessage());
        }    
       if(i==1200){
            try{
                  URL url=new URL("http://www.google.com");
                  URLConnection con=url.openConnection();
                  con.connect();
                  System.out.println("Connected");
                  i=1800;
                  dispose();
                  new selector(); 
            }
            catch(Exception e){
                  System.out.println("Sorry, No Internet connection");
                  JOptionPane.showMessageDialog(null,"Sorry, No Internet connection");
                  i=2001;
                  dispose();
            }
        }
       
    }  
 } 
    
}
