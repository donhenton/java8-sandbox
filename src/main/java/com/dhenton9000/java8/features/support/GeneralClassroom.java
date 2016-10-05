/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features.support;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author dhenton
 */
public class GeneralClassroom implements Classroom{
    
    
    private final ArrayList<Person> students = new ArrayList<Person>();
    private Function<List<Person>,String> displayMethod;
    /**
     * @return the students
     */
    public ArrayList<Person> getStudents() {
        return students;
    }
   
    @Override
    public void add(Person p) {
         getStudents().add(p);
    }
    
    public GeneralClassroom(Function<List<Person>,String> e)
    {
        this.displayMethod = e;
    }

    @Override
    public String display() {
        return this.getDisplayMethod().apply(students);
    }

    /**
     * @return the displayMethod
     */
    public Function<List<Person>,String> getDisplayMethod() {
        return displayMethod;
    }

    /**
     * @param displayMethod the displayMethod to set
     */
    public void setDisplayMethod(Function<List<Person>,String> displayMethod) {
        this.displayMethod = displayMethod;
    }
}
