/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.functionalunittests;

import com.sg.functionalunittests.CanHazTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanHazTableTest {

    private CanHazTable canHazTable = new CanHazTable();

   // Verify: canHazTable(5, 10) → 2
    @Test
    public void test5And10(){
        assertEquals(2, canHazTable.canHazTable(5, 10));
    }
    
    // Verify: canHazTable(5, 2) → 0
    @Test
    public void test5And2(){
        assertEquals(0, canHazTable.canHazTable(5, 2));
    }
    
    // Verify: canHazTable(5, 5) → 1
    @Test
    public void test5And5(){
        assertEquals(1, canHazTable.canHazTable(5, 5));
    }
}