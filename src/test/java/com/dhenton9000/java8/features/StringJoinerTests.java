/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dhenton
 */
public class StringJoinerTests {

    private Logger log = LoggerFactory.getLogger(StreamTests.class);

    private List<String> testList = Arrays.asList("a", "b", "c");

    @Test
    public void testStringJoiner() {

        StringJoiner joiner = new StringJoiner("," );
        joiner.add("01").add("02").add("03");
        String joinedString = joiner.toString(); // "01,02,03"
        assertEquals("01,02,03",joinedString);
    }

}
