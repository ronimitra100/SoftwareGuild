package com.sg.functionalunittests;

import com.sg.functionalunittests.SumDouble;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SumDoubleTest {
    private SumDouble sumDouble = new SumDouble();
    
    // Given two int values, return their sum. However, if the two 
    // values are the same, then return double their sum. 
    //
    // Verify: sumDouble(1, 2) -> 3
    @Test
    public void test1And2(){
        assertEquals(3, sumDouble.sumDouble(1, 2));
    }
    
    // Verify: sumDouble(3, 2) -> 5
    @Test
    public void test3And2(){
        assertEquals(5, sumDouble.sumDouble(3, 2));
    }
    
    // Verify: sumDouble(2, 2) -> 8
    @Test
    public void test2And2(){
        assertEquals(8, sumDouble.sumDouble(2, 2));
    }
    
}