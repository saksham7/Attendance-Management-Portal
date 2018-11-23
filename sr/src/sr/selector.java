
package sr;
import java.io.IOException;
import java.util.ArrayList;
import static sr.JsonREader.readJsonFromUrl;
import org.json.JSONException;
import org.json.JSONArray;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;   

public class selector extends JFrame{
    private JComboBox mainComboBox;
    private JComboBox subComboBox;
    private JComboBox batchComboBox;
    JPasswordField key;
    private JButton btn;
    private Hashtable subItems = new Hashtable();
    JLabel lbl1,lbl2,lbl3,lbl4,head,logo;
    
    public selector() throws IOException, JSONException {
    
    ArrayList<String> list = new ArrayList<String>(); 
    //Map<String,String> map=new HashMap<String,String>();
     setLayout(null);
     lbl1=new JLabel("Select Department :");
     lbl2=new JLabel("Select Year :");
     lbl3=new JLabel("Select Batch :");
     lbl4=new JLabel("Enter Key :");
     head=new JLabel("Enter Details to Access Portal");
     logo=new JLabel(new ImageIcon("C:\\Users\\Saksham's PC\\Documents\\NetBeansProjects\\sr\\src\\images\\logo.jpg"));
     add(lbl1);
     add(lbl2);
     add(lbl3);
     add(lbl4);
     add(head);
     getContentPane().add(logo);
     lbl1.setBounds(240,170,120,30);
     lbl2.setBounds(240,230,120,30);
     lbl3.setBounds(240,290,120,30);
     lbl4.setBounds(240,350,120,30);
     head.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 21));
     head.setBounds(255,100,400,60);
     logo.setBounds(0,10,800,100);
     
     btn=new JButton("Submit");
     add(btn);
     btn.setBounds(320,410,100,25);
     btn.setBackground(Color.white);
//    if (json != null) { 
//       int len = json.length();
//       //System.out.println(len);
//       for (int i=0;i<len;i++){ 
//          // map.put("name",get("name"));
//          // map.put("rollno",get("rollno"));
//        list.add(json.get(i).toString());
//       } 
//    } 
      String[] items = { "Select Department", "Computer Science", "Information Technology", "Mechanical Engineering","Civil Engineering",
          "Electrical Engineering","Electronics & Comm. Engineering","Agricultural Engineering" ,"Bio-Technology"};
        mainComboBox = new JComboBox( items );
      
 
        //  prevent action events from being fired when the up/down arrow keys are used
        mainComboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        add( mainComboBox);
        mainComboBox.setBounds(390,170,130,35);
 
        //  Create sub combo box with multiple models
        String[] subItems2 = { "Select Year", "1", "2", "3", "4" };
        subComboBox = new JComboBox(subItems2);
        subComboBox.setPrototypeDisplayValue("Select Year"); // JDK1.4
        add(subComboBox);
        subComboBox.setBounds(390,230,130,35);
 
        String[] subItems3 = { "Select Batch", "A", "B", "C" };
        batchComboBox=new JComboBox(subItems3);
        subComboBox.setPrototypeDisplayValue("Select Batch"); 
        add(batchComboBox);
        batchComboBox.setBounds(390,290,130,35);
        
        key=new JPasswordField();
        add(key);
        key.setBounds(390, 350, 130, 35);
        
    btn.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
        String dept = (String)mainComboBox.getSelectedItem();
        String year = (String)subComboBox.getSelectedItem();
        String batch = (String)batchComboBox.getSelectedItem();
        String key1=key.getText().toString();
            try {
                switch(dept){
		case "Computer Science" : dept="10"; break;
		case "Civil Engineering" : dept="00"; break;
		case "Information Technology" : dept="13"; break;
		case "Electronics & Comm. Engineering" : dept="31"; break;
	       }
                JSONArray json = readJsonFromUrl("http://localhost/sr/details.php?dept="+dept+"&year="+year+"&batch="+batch+"&key="+key1);
                String a=json.get(0).toString();
                String date=json.get(1).toString();
                if(a.equals("Error")){
                    JOptionPane.showMessageDialog(null,"Sorry,Register doesnot Exist...Contact Admin !!!");
                }
                else{
                    dispose();
                    new entry(dept,year,batch,date,a);
                }
             
            } catch (IOException ex) {
                Logger.getLogger(selector.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(selector.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    });
    getContentPane().setBackground(new Color(224, 224, 209));
      setBounds(300,100,800,600);
       setResizable(false);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true); 
    }
    
    

    
}
