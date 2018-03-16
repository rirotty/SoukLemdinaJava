/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.tests;

import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Profile;
import com.souklemdina.services.ProfileServices;
import java.util.Date;

/**
 *
 * @author ramyk
 */
public class TestProfile {
    public static void main(String[] args) {
        FosUser f = new FosUser(9);
        Profile p1 = new Profile("Hey There", "/jhdwhw/", new Date(System.currentTimeMillis()), "I'm the administrator here", f);
        ProfileServices ps = new ProfileServices();
        
        ps.create(p1);
    }
}
