 
package com.dhenton9000.java8.features;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * @see http://stackoverflow.com/questions/25055392/lambdas-local-variables-need-final-instance-variables-dont
 * 
 * instance variables can be modified by lambdas, but local variables must
 * be final or effectively final.
 * 
 * @author dhenton
 */
public class FinalVariableAndLambdaTests {
    
    
    private int counter = 0;
    
    
    @Before
    public void testBefore()
    {
        counter = 0;
    }
    
    @Test
    public void testAccess()
    {
        Arrays.asList(1,2,3,4,5).stream().forEach(a ->{
        
        counter += a;
        
        
        });
        
        assertEquals(15,counter);
    }
     /*
    this will not compile because counterVar must be final or effectively final
    @Test
    public void testAccessLocal()
    {
        int counterVar = 0;
        Arrays.asList(1,2,3,4,5).stream().forEach(a ->{
        
        counterVar += a;
        
        
        });
        
        assertEquals(15,counterVar);
    }
*/
     
    
}
