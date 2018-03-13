/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Local;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface ILocalServices {

    public void create(Local l);

    public List<Local> findAll();

    public void update(Local l);

    public void delete(Local l);
}
