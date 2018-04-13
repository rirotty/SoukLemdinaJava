/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Local;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ramyk
 */
public interface ILocalServices {

    public void ajoutLocal(Local l, FileInputStream fis, File file);

    public ObservableList<Local> afficherLocal();
    
     public ObservableList<Local> afficherLocal2();

    public List<Local> afficherLocalUser(int idUser);
    
    public Local afficherLocalUn(int id);

    public void modifierLocal(Local l);

    public void supprimerLocal(int id);
}
