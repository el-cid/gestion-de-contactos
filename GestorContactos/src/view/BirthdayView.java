/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author mizar
 */
public class BirthdayView extends JPanel{
    private JDatePickerImpl datePicker;
    private JLabel labelFecha = new JLabel("Fecha de nacimiento:");
    private UtilDateModel model = new UtilDateModel();
    private JDatePanelImpl datePanel;
    public BirthdayView(){
        this.add(labelFecha);
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        //datePicker.addActionListener( new DateL() );
        this.add(datePicker);
        this.setLayout( new FlowLayout() );
    }
    public void makeStatic( boolean b ){
            datePicker.getComponent(1).setEnabled( !b );
    }
    /*
    private class DateL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println(datePicker.getModel().getDay());
            System.out.println(datePicker.getModel().getMonth());
            System.out.println(datePicker.getModel().getYear());
            revalidate();
            repaint();
        }
    }
    */
    public UtilDateModel getDateModel(){
        return this.model;
    }
    
    public void update(){
        //System.out.println(model.getDay() + model.getMonth() + model.getYear());
    }
}
