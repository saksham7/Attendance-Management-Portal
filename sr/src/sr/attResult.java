/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sr;

import javax.swing.*;

public class attResult
{
    public attResult(int count)
    {
        

        ImageIcon icon = new ImageIcon("C:\\Users\\Saksham's PC\\Documents\\NetBeansProjects\\sr\\src\\images\\UPDATED.png");
        JOptionPane.showMessageDialog(null, count+" Students are present.", "Status", JOptionPane.INFORMATION_MESSAGE, icon);
        //dispose();
        
        
    }
   
}