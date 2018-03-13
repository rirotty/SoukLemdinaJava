/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Evenement;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IEvenementServices {

    public void create(Evenement e);

    public List<Evenement> findAll();

    public void update(Evenement e);

    public void delete(Evenement e);
}
