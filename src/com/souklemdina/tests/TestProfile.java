/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.tests;

import com.souklemdina.entities.Profile;
import com.souklemdina.services.ProfileServices;

/**
 *
 * @author ramyk
 */
public class TestProfile {
    public static void main(String[] args) {
        ProfileServices ps = new ProfileServices();
        ps.findAll().stream().map(a -> "Le profile d'id: " + a.getId() + " est de l'utilisateur: " + a.getIdUser() + " .").forEach(System.out::println);
        Profile p = new Profile(10,9);
        ps.delete(p);
    }
}
