/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.ParticipantWork;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface IParticipantWorkServices {

    public void create(ParticipantWork pw);

    public List<ParticipantWork> findAll();

    public void delete(ParticipantWork pw);
}
