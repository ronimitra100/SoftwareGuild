package com.sg.functionalunittests;

import com.sg.functionalunittests.PlayOutside;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayOutsideTest {
    private PlayOutside playOutside = new PlayOutside();
  
    // Verify: playOutside(70, false) → true
    @Test
    public void test70False(){
        assertTrue(playOutside.playOutside(70, false));
    }
    
    // Verify: playOutside(95, false) → false
    @Test
    public void test95False(){
        assertFalse(playOutside.playOutside(95, false));
    }
    
    // Verify: playOutside(95, true) → true
    @Test
    public void test95True(){
        assertTrue(playOutside.playOutside(95, true));
    }
    
}