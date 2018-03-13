/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Location;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface ILocationServices {

    public void create(Location l);

    public List<Location> findAll();

    public void update(Location l);

    public void delete(Location l);
}
