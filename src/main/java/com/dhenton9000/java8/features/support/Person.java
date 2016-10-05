/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features.support;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 *
 * @author dhenton
 */
public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;

    public Person() {
    }

     public int getAge()
     {
         LocalDate n = LocalDate.now();
         Period diff = Period.between( this.getBirthday(),n);
         float res = ((float) diff.getMonths())/12;
         float t = ((float) diff.getYears())  + res;
         //System.out.println(String.format("years %d monthfrac %2.2f",diff.getYears(),res));
         return Math.round(t);
          
     }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the gender
     */
    public Sex getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Sex gender) {
        this.gender = gender;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.emailAddress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "gender=" + gender + ", emailAddress=" + emailAddress+ ", bdate=" + birthday + '}';
    }

}
