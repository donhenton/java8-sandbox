/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features.support;

import java.util.ArrayList;

/**
 *
 * @author dhenton
 */
public abstract class BaseClassroom implements Classroom {
    
    private final ArrayList<Person> students = new ArrayList<Person>();
    
     

    @Override
    public void add(Person p) {
         getStudents().add(p);
    }

    /**
     * @return the students
     */
    public ArrayList<Person> getStudents() {
        return students;
    }
}
