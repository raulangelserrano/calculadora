/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg07calculadora;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author alumno
 */
public class CapturaTeclado implements KeyListener{
      @Override
      public void keyTyped(KeyEvent e) {
        //    System.out.print("teclatyped");
      }
      @Override
      public void keyPressed(KeyEvent e) {
         System.out.print(e.getKeyCode());
      }
      @Override
      public void keyReleased(KeyEvent e) {
       //     System.out.print("teclareleased");
      }   
   }
