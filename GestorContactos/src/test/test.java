/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import control.Controller;
import view.ViewController;

/**
 *
 * @author mizar
 */
public class test {
    public static void main(String[] args) {
       Controller control = new Controller();
       ViewController main = new ViewController();
       main.setController(control);
       control.setMainView(main);
    } 
}
