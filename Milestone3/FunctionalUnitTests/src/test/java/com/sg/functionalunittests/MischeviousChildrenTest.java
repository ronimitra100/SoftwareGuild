package com.sg.functionalunittests;

import com.sg.functionalunittests.MischeviousChildren;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MischeviousChildrenTest {
    private MischeviousChildren mischeviousChildren = new MischeviousChildren();
    
    // Verify: areWeInTrouble(true, true) -> true
    @Test
    public void testBothSmiling(){
        assertTrue(mischeviousChildren.areWeInTrouble(true, true));
    }
    
    //Verify:  areWeInTrouble(false, false) -> true
    @Test
    public void testNeitherSmiling(){
        assertTrue(mischeviousChildren.areWeInTrouble(false, false));
    }
    
    //Verify:  areWeInTrouble(true, false) -> false
    @Test
    public void testASmilingButBNotSmiling(){
        assertFalse(mischeviousChildren.areWeInTrouble(true, false));
    }
}