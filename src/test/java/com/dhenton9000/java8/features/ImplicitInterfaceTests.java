/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features;

import java.util.function.Supplier;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * These tests illustrate functions 'masquarading' as functional interfaces
 *
 * Functions can be submitted as parameters to other functions which require a
 * given interface.
 *
 *
 *
 * @author dhenton
 */
public class ImplicitInterfaceTests {

    private Logger log = LoggerFactory.getLogger(FunctionalInterfaceTests.class);

    @Test
    public void testSupplierTypical() {

        Supplier<String> s = () -> {
            return "fred";
        };
        
        assertEquals("get a job, fred",(new TestConsumer()).report(s));

    }
    
    
    /**
     * TesCreation::new meets the Supplier functional interface definition
     * for suppling a Reporter. This allows 'aliasing' function calls. The
     * constructor for TestCreation has now been cast to Supplier.get()
     * 
     */
     @Test
    public void testSupplierCanAcceptAnyCreationMethodAsLongAsTheSignatureIsOK() {

        assertEquals("get a reporter job, TestCreation",
                (new TestConsumer()).reportReporter(TestCreation::new));

    }
    
    private interface Reporter
    {
        
    }

    private class TestCreation implements Reporter
    {

        @Override
        public String toString() {
            return "TestCreation";
        }
        
    }

    private class TestConsumer {

        public String report(Supplier<String> s) {
            return "get a job, " + s.get();
        }

         public String reportReporter(Supplier<Reporter> s) {
            return "get a reporter job, " + s.get();
        }
        
    }

}
