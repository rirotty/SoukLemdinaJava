/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Location;

import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ramyk
 */
public interface ILocationServices {

    public void ajoutLocation(Location l);

    public ObservableList<Location> afficherLocation(int id);

    public ObservableList<Location> afficherTout(int id);

    public ObservableList<Location> afficherLocationUser(int id);

    public void supprimerLocation(int id);

}
