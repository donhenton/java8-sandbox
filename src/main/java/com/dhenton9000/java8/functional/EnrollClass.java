/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.functional;

import com.dhenton9000.java8.features.support.Classroom;
import com.dhenton9000.java8.features.support.Person;


/**
 *
 * @author dhenton
 */
@FunctionalInterface
public interface EnrollClass {
    
    public void enroll(Classroom c, Person p);
    
}
