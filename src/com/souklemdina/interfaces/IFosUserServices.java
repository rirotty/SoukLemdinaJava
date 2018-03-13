/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.FosUser;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface IFosUserServices {

    public void create(FosUser u);

    public List<FosUser> findAll();

    public void update(FosUser u);

    public void delete(FosUser u);
}
