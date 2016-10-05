/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features;

import com.dhenton9000.java8.features.support.Person;
import com.dhenton9000.java8.features.support.PersonFactory;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dhenton
 */
public class DoubleColonTests {

    private Logger log = LoggerFactory.getLogger(DoubleColonTests.class);
    private PersonFactory f = new PersonFactory();

    private static Integer cutByHalf(Person p) {
        return p.getAge() / 10;
    }

    @Test
    public void testFindByList() {

        List<String> t = f.getData().stream()
                .map(DoubleColonTests::cutByHalf)
                .map((i) -> {
                    return i + "";
                })
                .collect(Collectors.toList());
        String zz = String.join(",", t);
        assertEquals("4,2,7,7,1",zz);
    }
}
