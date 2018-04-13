/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author ACER
 */
public class TextFieldValidation {
    
    public static boolean isTextFieldNotEmpty(TextField tf){
     boolean  b = false;
     if (tf.getText().length() != 0  || !tf.getText().isEmpty()){
     b = true;
     
     }
     return b;
    }
    
    
     public static boolean isTextFieldNotEmpty(TextField tf,Label lb , String msgErr){
     boolean  b = true;
    String msg = null; 
    if (! isTextFieldNotEmpty(tf)){
    b = false;
    msg= msgErr;
    }    
    lb.setText(msg);
     
     return b;
    }
      public static boolean isTextAreaNotEmpty(JFXTextArea tf){
     boolean  b = false;
     if (tf.getText().length() != 0  || !tf.getText().isEmpty()){
     b = true;
     
     }
     return b;
    }
      
        public static boolean isTextAreaNotEmpty(JFXTextArea tf,Label lb , String msgErr){
     boolean  b = true;
    String msg = null; 
    if (! isTextAreaNotEmpty(tf)){
    b = false;
    msg= msgErr;
    }    
    lb.setText(msg);
     
     return b;
    }
     
       public static boolean isTextFieldNumber(TextField tf){
     boolean  b = false;
     if (tf.getText().matches("([0-9]+(\\.[0-9]+)?)+")){
     b = true;
     
     }
     return b;
    }
       
           public static boolean isTextFieldNumber(TextField tf,Label lb , String msgErr){
     boolean  b = false;
     String msg = null;
     if (! isTextFieldNumber(tf)){
     b = true;
     msg = msgErr;
     }
     lb.setText(msg);
     return b;
    }
     
     
    
}
