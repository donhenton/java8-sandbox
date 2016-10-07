/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features;

import com.dhenton9000.java8.features.support.IPersonSource;
import com.dhenton9000.java8.features.support.Person;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author dhenton
 */
@RunWith(MockitoJUnitRunner.class)
public class OptionalTests {

    private Logger log = LoggerFactory.getLogger(OptionalTests.class);

    @Mock
    private IPersonSource personSource;

    private Person p1;

    @Before
    public void prepTest() {

        p1 = new Person();
        p1.setName("Fred Farkel");
        p1.setGender(Person.Sex.MALE);
        p1.setBirthday(LocalDate.now().minus(36, ChronoUnit.YEARS));
        given(personSource.find(eq(5))).willReturn(p1);
        Answer<Optional<Person>> answer = (InvocationOnMock invocation) -> {
            Integer request = invocation.getArgumentAt(0, Integer.class);
            Person stub;
            switch (request) {
                case 5:
                    stub = p1;
                    break;

                default:
                    stub = null;

            }
            return Optional.ofNullable(stub);

        };
        //given(personSource.findOptional(AdditionalMatchers.not(eq(5)))).willReturn(Optional.empty());
        //given(personSource.findOptional(eq(5))).willReturn(Optional.of(p1));
        given(personSource.findOptional(anyInt())).willAnswer(answer);

    }

    @Test
    public void testMocking() {
        Person pSample = personSource.find(5);
        assertNotNull(pSample);
        assertEquals(pSample, p1);
    }

    @Test
    public void testMockingNotFound() {
        Person pSample = personSource.find(234);
        assertNull(pSample);

    }

    @Test
    public void testOptional() {
        Optional<Person> pSample = personSource.findOptional(5);
        assertNotNull(pSample);
        assertTrue(pSample.isPresent());
        assertEquals(pSample.get(), p1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testOptionalNotPresent() {
        Optional<Person> pSample = personSource.findOptional(25);
        assertNotNull(pSample);
        assertFalse(pSample.isPresent());
        assertNull(pSample.get());

    }

    @Test(expected = NullPointerException.class)
    public void testOptionalOfThrowsNull() {
        String t = null;
        Optional p = Optional.of(t);

    }

    @Test
    public void testOptionalOfNullableDoesNotThrowsNull() {
        String t = null;
        Optional p = Optional.ofNullable(t);
        assertFalse(p.isPresent());

    }

    @Test
    public void testIfOptional() {

        final List<String> tList = new ArrayList<String>();
        personSource.findOptional(5).ifPresent(p -> tList.add(p.getName()));
        assertEquals(p1.getName(), tList.get(0));

    }

    @Test
    public void testIfOptionalNot() {

        final List<String> tList = new ArrayList<String>();
        personSource.findOptional(25).ifPresent(p -> tList.add(p.getName()));
        assertEquals(0, tList.size());

    }

}
