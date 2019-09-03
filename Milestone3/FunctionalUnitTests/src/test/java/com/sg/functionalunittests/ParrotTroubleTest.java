package com.sg.functionalunittests;

import com.sg.functionalunittests.ParrotTrouble;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParrotTroubleTest {
    private ParrotTrouble parrotTrouble = new ParrotTrouble();
    
    // Verify: parrotTrouble(true, 6) -> true
    @Test
    public void testParrotTalkingAt6(){
        assertTrue(parrotTrouble.parrotTrouble(true, 6));
    }
    
    // Verify: parrotTrouble(true, 7) -> false
    @Test
    public void testParrotTalkingAt7(){
        assertFalse(parrotTrouble.parrotTrouble(true, 7));
    }
    
    // Verify: parrotTrouble(false, 6) -> false
    @Test
    public void testParrotNotTalkingAt6(){
        assertFalse(parrotTrouble.parrotTrouble(false, 6));
    }
    
}