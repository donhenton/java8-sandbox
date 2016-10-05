/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features;

import com.dhenton9000.java8.features.support.Person;
import com.dhenton9000.java8.features.support.PersonFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.tutorialspoint.com/java8/java8_functional_interfaces.htm
 * @author dhenton
 */
public class FunctionalInterfaceTests {

    private Logger log = LoggerFactory.getLogger(FunctionalInterfaceTests.class);
    private PersonFactory f = new PersonFactory();

    @Test
    public void testPredicateFunctionalInterface() {

        class NameFilter implements Predicate<Person> {

            private final String filter;

            public NameFilter(String s) {
                this.filter = s;
            }

            @Override
            public boolean test(Person p) {
                return p.getName().toLowerCase().contains(filter);
            }

        }
        Predicate<Person> myFilter = new NameFilter("nixon");

        List<Person> found = f.getData().stream().filter(myFilter).collect(Collectors.toList());

        assertEquals(1, found.size());

        assertEquals(79, found.get(0).getAge());

    }

    @Test
    public void testMapFunctionalInterface() {
        class MapperItem implements Function<Person, String> {

            @Override
            public String apply(Person t) {
                return t.getName();
            }

        }

        MapperItem myMapper = new MapperItem();

        List<String> found = f.getData().stream().filter((Person p) -> {
            LocalDate other = LocalDate.of(1960, Month.JANUARY, 1);
            return p.getBirthday().isBefore(other);
        })
                .map(myMapper)
                .collect(Collectors.toList());
        
        assertEquals(2, found.size());
        assertTrue(found.contains("Marnie Nixon"));

    }

}
