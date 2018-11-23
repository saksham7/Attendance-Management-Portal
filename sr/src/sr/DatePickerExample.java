package sr;

//import statements
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import static sr.JsonREader.readJsonFromUrl;

//create class and extend with JFrame
public class DatePickerExample extends JFrame
{	
		//add JPanel to the contentPane
		private JPanel contentPane;
		//declare variable
		private JTextField txtDate;
	
	public DatePickerExample(String res) throws JSONException, IOException 
	{
		//set title
		setTitle("View Attendance");
		
		setBounds(300,100,800,600);
		//create new JPanel in contentPane
		contentPane = new JPanel();
		//set border of frame
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//set contentPane 
		setContentPane(contentPane);
		//set layout null
		contentPane.setLayout(null);
		
		//create text field
		txtDate = new JTextField();
                txtDate.setEnabled(false);
                JLabel date=new JLabel("Date:");
                add(date);
                date.setBounds(20,60,130, 30);
		//set bounds of text field
		txtDate.setBounds(180, 60,90, 25);
		
		contentPane.add(txtDate);
	
		JLabel txt=new JLabel("View Attendance");
                add(txt);
                txt.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 21));
                txt.setBounds(300,10,400,60);
      
		//create button and there object
		JButton btnNewButton = new JButton("Select Date");
                btnNewButton.setBounds(60,60,100,25);
		//perform action listener
		btnNewButton.addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent arg0) 
			{
				//create frame new object  f
				final JFrame f = new JFrame();
				//set text which is collected by date picker i.e. set date 
				txtDate.setText(new DatePicker(f).setPickedDate());
                                if(txtDate.getText().equals("")) {
                                }
                                else {
                                    String text = txtDate.getText();
                                JScrollPane scroll=new JScrollPane();
       scroll.setBounds(30,100,720,400);
       add(scroll);
                final JTable table=new JTable() {
        public boolean isCellEditable(int row, int column) {                
                return false;               
        }
    }; 

    table.getTableHeader().setReorderingAllowed(false);
    table.setRowHeight(40);
    scroll.setViewportView(table);
    table.setGridColor(Color.green);
    table.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 18));

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
          default:
            return String.class;
        }
      }
    };
    //ASSIGN THE MODEL TO TABLE
    table.setModel(model);
    table.setLocation(50,150);
    model.addColumn("Roll No.");
    model.addColumn("Name");
    
    //Values
    JSONArray json = null;
                            try {
                                json = readJsonFromUrl("http://localhost/sr/view.php?ResId="+res+"&date="+text);
                            } catch (IOException ex) {
                                Logger.getLogger(DatePickerExample.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (JSONException ex) {
                                Logger.getLogger(DatePickerExample.class.getName()).log(Level.SEVERE, null, ex);
                            }
    ArrayList<String> list = new ArrayList<String>(); 
//Map<String,String> map=new HashMap<String,String>();

if (json != null) { 
   int len = json.length();
   for (int i=0;i<len;i++){ 
       try {
           // map.put("name",get("name"));
           // map.put("rollno",get("rollno"));
           list.add(json.get(i).toString());
       } catch (JSONException ex) {
           Logger.getLogger(DatePickerExample.class.getName()).log(Level.SEVERE, null, ex);
       }
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
    }

    //OBTAIN Selected ROW
   
			}
                        }
		});
		//set button bound
		
		//add button in contentPane
                setResizable(false);
		contentPane.add(btnNewButton);
                setDefaultCloseOperation(HIDE_ON_CLOSE);
                setVisible(true);
	}
      
}