
package sr;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import static sr.JsonREader.readJsonFromUrl;

public class attTable extends JFrame {
    JLabel txt;
    attResult a;
   public attTable(String dept,String year,String batch,String date,String a) throws IOException, JSONException{
       
       setTitle("Attendance Portal");
       setLayout(null);
       txt=new JLabel("Submit Today's Attendance");
       add(txt);
    //lbl2.setForeground(Color.white);
       txt.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 21));
       txt.setBounds(255,10,400,60);
      
       JScrollPane scroll=new JScrollPane();
       scroll.setBounds(30,60,720,400);
       add(scroll);

    //THE TABLE
    final JTable table=new JTable() {
        public boolean isCellEditable(int row, int column) {                
                return column ==2;               
        }
    }; 
    table.getTableHeader().setReorderingAllowed(false);
    scroll.setViewportView(table);
    table.setGridColor(Color.green);
    table.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 18));
    table.setRowHeight(0, 30);

    //THE MODEL OF OUR TABLE
    DefaultTableModel model=new DefaultTableModel()
    { 
      public Class<?> getColumnClass(int column)
      {
        switch(column)
        {
        case 0:
          return String.class;
        case 1:
          return String.class;
        case 2:
          return Boolean.class;
          default:
            return String.class;
        }
      }
    };
    //ASSIGN THE MODEL TO TABLE
    table.setModel(model);
    table.setRowHeight(40);
    
    model.addColumn("Roll No.");
    model.addColumn("Name");
    model.addColumn("Present");
    
    //Values
    JSONArray json = readJsonFromUrl("http://localhost/sr/test.php?DeptNo="+dept+"&DeptYearId="+year+"&BatchId="+batch);
    ArrayList<String> list = new ArrayList<String>(); 
//Map<String,String> map=new HashMap<String,String>();

if (json != null) { 
   int len = json.length();
   for (int i=0;i<len;i++){ 
      // map.put("name",get("name"));
      // map.put("rollno",get("rollno"));
    list.add(json.get(i).toString());
   } 
} 
ArrayList<String> var1 = new ArrayList<String>();
ArrayList<String> var2 = new ArrayList<String>();
for(int i=0;i<list.size();i++) {
    if(i%2==0) {
        var1.add(list.get(i));
    }
    else {
        var2.add(list.get(i));
    }
}

    //THE ROW
    for(int i=0;i<var1.size();i++)
    {
      model.addRow(new Object[0]);
      model.setValueAt(var1.get(i), i, 0);
      model.setValueAt(var2.get(i), i, 1);
      model.setValueAt(false,i,2);
    }

    //OBTAIN Selected ROW
    JButton btn=new JButton("Submit Attendace");
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        //GET Selected ROW
        int count=0;
        for(int i=0;i<table.getRowCount();i++)
        {
          Boolean checked=Boolean.valueOf(table.getValueAt(i, 2).toString());
          String col=table.getValueAt(i, 0).toString();

          //DISPLAY
          if(checked)
          {
               try {
            // open a connection to the site
            URL url = new URL("http://localhost/sr/insert.php");
            URLConnection con = url.openConnection();
            // activate the output
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            // send your parameters to your site
            ps.print("&RollNo=" + col);     
            //ps.print("&api=");
            ps.print("&registerno=" + a);
            ps.print("&date=" + date);
            // we have to get the input stream in order to actually send the request
            con.getInputStream();

            // close the print stream
            ps.close();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            count++;
          }
        }
        //ImageIcon icon = new ImageIcon("C:\\Users\\lala\\Documents\\NetBeansProjects\\sr\\src\\sr\\UPDATED.png");
        //JOptionPane.showMessageDialog(null, count+" students are present.", "Status", JOptionPane.INFORMATION_MESSAGE, icon);
        dispose();
       new attResult(count);
        
      }
    });

    //ADD BUTTON TO FORM
    btn.setBounds(320,490,140,30);
    add(btn);
    btn.setBackground(Color.white);
 
       getContentPane().setBackground(new Color(224, 224, 209));
       setBounds(300,100,800,600);
       setResizable(false);
       setDefaultCloseOperation(HIDE_ON_CLOSE);
       setVisible(true);
   } 
   /*public static void main(String[] ar) throws IOException, JSONException{
       new attTable();
   } */
}

