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
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://docs.oracle.com/javase/tutorial/collections/streams/index.html
 * https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 *
 * @author dhenton
 */
public class StreamTests {

    private Logger log = LoggerFactory.getLogger(StreamTests.class);
    private PersonFactory f = new PersonFactory();

    @Test
    public void testFindByList() {

        List<Person> found = f.getData().stream().filter((Person p) -> {
            return p.getName().toLowerCase().contains("nixon");
        }).collect(Collectors.toList());

        assertEquals(1, found.size());

        assertEquals(79, found.get(0).getAge());

    }

    @Test
    public void testFindAny() {

        Person found = f.getData().stream().filter((Person p) -> {
            return p.getName().toLowerCase().contains("nixon");
        }).findAny().orElse(null);

        assertNotNull(found);
        assertEquals(79, found.getAge());

    }

    @Test
    public void testFindAnyYieldNull() {

        Person found = f.getData().stream().filter((Person p) -> {
            return p.getName().toLowerCase().contains("zzzznixon");
        }).findAny().orElse(null);

        assertNull(found);

    }

    @Test
    public void testMapTheStream() {

        List<Person> found = f.getData().stream().filter((Person p) -> {
            LocalDate other = LocalDate.of(1960, Month.JANUARY, 1);
            return p.getBirthday().isBefore(other);
        }).collect(Collectors.toList());

        assertEquals(2, found.size());

    }

    @Test
    public void testMapTheStreamAndModifyIt() {

        List<Person> found = f.getData().stream().filter((Person p) -> {
            LocalDate other = LocalDate.of(1960, Month.JANUARY, 1);
            return p.getBirthday().isBefore(other);
        })
                .map((Person p) -> {
                    p.setName(p.getName() + "zzz");
                    return p;
                })
                .collect(Collectors.toList());

        assertEquals(2, found.size());

        found.forEach(p -> {
            assertTrue(p.getName().contains("zzz"));
        });
        
        
        f.getData().forEach(p -> {
            assertFalse(p.getName().contains("zzz"));
        });

    }

}
