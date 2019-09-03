package com.sg.functionalunittests;

import com.sg.functionalunittests.FrontTimes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FrontTimesTest {

    FrontTimes frontTimes = new FrontTimes();
    
    // Verify: frontTimes("Chocolate", 2) -> "ChoCho"
    @Test
    public void testChocolate2(){
        String expectedResult = "ChoCho";
        assertEquals(expectedResult, frontTimes.frontTimes("Chocolate", 2));
    }
    
    // Verify: frontTimes("Chocolate", 3) -> "ChoChoCho"
    
    @Test
    public void testChocolate3(){
        String expectedResult = "ChoChoCho";
        assertEquals(expectedResult, frontTimes.frontTimes("Chocolate", 3));
    }
    // frontTimes("Abc", 3) -> "AbcAbcAbc"
  
    @Test
    public void testAbc3(){
        String expectedResult = "AbcAbcAbc";
        assertEquals(expectedResult, frontTimes.frontTimes("Abc", 3));
    }
}