/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.tests;

import com.souklemdina.entities.Post;
import com.souklemdina.services.PostServices;

/**
 *
 * @author ramyk
 */
public class TestPost {
    
    public static void main(String[] args) {
        PostServices ps = new PostServices();
        ps.findAllFollowed(5, 3).forEach(e -> System.out.println(e.getTitre()));
    }
}
