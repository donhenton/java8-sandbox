/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features.support;

 
import java.util.stream.Collectors;

/**
 *
 * @author dhenton
 */
public class AgeClassroom extends BaseClassroom   {

    @Override
    public String display() {
        return  this.getStudents().stream().map(e-> e.getAge()+"").collect(Collectors.joining(","));
    }

    
    
}
