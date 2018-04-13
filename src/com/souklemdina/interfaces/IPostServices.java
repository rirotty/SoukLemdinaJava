/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Post;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface IPostServices {

    public void create(Post p);

    public List<Post> findAll();

    public void update(Post p);

    public void delete(Post p);
    
    public void signaler(Post p);
    
    public void unSignaler(Post p);
    
    public List<Post> getSignal();
    
    public void deleteById(Integer id);
    
    public List<Post> findByIdUser(Integer idUser);
    
    public Post findById(Integer id);
    
    public List<Post> findAllFollowed(Integer idProfileFollower, Integer idUserFollower);
}
