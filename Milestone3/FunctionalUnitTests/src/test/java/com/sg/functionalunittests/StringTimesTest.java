/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.functionalunittests;

import com.sg.functionalunittests.StringTimes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ronim_000
 */
public class StringTimesTest {
    private StringTimes stringTimes = new StringTimes();
    
    //Verify: stringTimes("Hi", 2) -> "HiHi"

    @Test
    public void testHiTimes2(){
        String expectedResult = "HiHi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 2));
    }
    
    //Verify: stringTimes("Hi", 1) -> "Hi"
     @Test
    public void testHiTimes1(){
        String expectedResult = "Hi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 1));
    }
    
     //Verify: stringTimes("Hi", 3) -> "HiHiHi"
      @Test
    public void testHiTimes3(){
        String expectedResult = "HiHiHi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 3));
    }
}