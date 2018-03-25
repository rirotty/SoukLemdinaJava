/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Workshop;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface IWorkshopServices {

    public void create(Workshop w);

    public List<Workshop> findAll();

    public void update(Workshop w);

    public void delete(Workshop w);
}
