/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.tests;

import com.souklemdina.entities.FosUser;
import com.souklemdina.services.FosUserServices;
import java.sql.Date;

/**
 *
 * @author hatem
 */
public class TestFosUser {

            //FosUserServices e = new FosUserServices("Hatem", "Hatem","hatem.abrouz@esprit.tn","hatem.abrouz@esprit.tn",
    //1,"","pass",2018-02-28 00:57:36,"","homme","hatem","ab","20 mars",1074,"ben arous","tunisie",29659174,0,1994-09-15);
/*FosUser fu = new FosUser(Integer.SIZE, "hatem",
     null, null, null, true, null, null, null,
     null, null, null, null, null, null, null,
     Integer.SIZE, null, null, null,
     Integer.SIZE, null);*/
    public static void main(String[] args) {
        FosUserServices es = new FosUserServices();
        es.create(new FosUser(null, "hatem", "hatem", "hatem.abrouz@esprit.tn", "hatem.abrouz@esprit.tn",
                true, "pass", null, new Date(18, 2, 15), null, null, null, "homme", "hatem", "ab", "20 mars",
                new Integer(2074), "ben arous", "tunisie", "28596447", new Integer(0), new Date(94, 9, 15)));
        
        System.out.println("Avant delete ****************************");
        es.findAll().forEach(System.out::println);
    }

}
