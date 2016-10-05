/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author dhenton
 */
public class PersonFactory {
    //name, birthday,email,gender

    private String[][] data = {
        {"Fred Farkel", "09/22/1967", "fred.farkel@buzzcut.com","MALE"},
        {"John Smith", "03/12/1988", "john.smith@buzzcut.com","MALE"},
        {"Tempest Bledsoe", "02/22/1947", "overacheiver@buzzcut.com","FEMALE"},
        {"Marnie Nixon", "11/22/1937", "high.class.yodeler@buzzcut.com","FEMALE"},
        {"David Dukes", "09/22/2000", "imperial.wizard@buzzcut.com","MALE"}};

    public List<Person> getData() {
        Stream<String[]> dataStream = Arrays.stream(data);

        Stream<Person> stuff = dataStream.map((String[] i) -> {

            Person p = new Person();
            p.setName(i[0]);
            p.setEmailAddress(i[2]);
            p.setGender(Person.Sex.valueOf(i[3]));
            DateTimeFormatter d = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate t = LocalDate.parse(i[1],d);
            p.setBirthday(t);
            
            
            return p;
        });
        
        return Arrays.asList(stuff.toArray(size -> new Person[size]));

    }

    //https://docs.oracle.com/javase/tutorial/datetime/iso/format.html
    
    public static void main(String[] args)
    {
        PersonFactory f = new PersonFactory();
        f.getData().forEach(t ->System.out.println(t));
        
    }
    
    
    
    
}
