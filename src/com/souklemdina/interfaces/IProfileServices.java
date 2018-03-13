/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Profile;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface IProfileServices {

    public void create(Profile p);

    public List<Profile> findAll();

    public void update(Profile p);

    public void delete(Profile p);
}
