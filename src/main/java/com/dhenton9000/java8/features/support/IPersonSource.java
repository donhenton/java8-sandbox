/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features.support;

import java.util.Optional;

/**
 *
 * @author dhenton
 */
public interface IPersonSource {
    
    
   Person find(Integer personId);
   Optional<Person> findOptional(Integer personId); 
   
}
