package com.sg.functionalunittests;

import com.sg.functionalunittests.SameFirstLast;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SameFirstLastTest {
    private SameFirstLast sameFirstLast = new SameFirstLast();
    
    // Verify: sameFirstLast({1, 2, 3}) -> false
    @Test
    public void testArrayOfLength3WithDifferentFirstAndLast(){
        int[] arr = {1, 2, 3};
        assertFalse(sameFirstLast.sameFirstLast(arr));
    }
    
    // Verify: sameFirstLast({1, 2, 3, 1}) -> true
    @Test
    public void testArrayOfLength4WithSameFirstAndLast(){
        int[] arr = {1, 2, 3, 1};
        assertTrue(sameFirstLast.sameFirstLast(arr));
    }
    
    // Verify: sameFirstLast({1, 2, 1}) -> true
    @Test
    public void testArrayOfLength3WithSameFirstAndLast(){
        int[] arr = {1, 2, 1};
        assertTrue(sameFirstLast.sameFirstLast(arr));
    }        
            
}