/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
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
    public BirthdayView(){
        this.add(labelFecha);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        this.add(datePicker);
        this.setLayout( new FlowLayout() );
    }
}
