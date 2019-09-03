/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section03unittests;

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
public class SayHiTest {
    
    private SayHi sayHi = new SayHi();
    public SayHiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

 
    @Test
    public void testBob(){
        String expectedResult = "Hello Bob!";
        assertEquals(expectedResult,sayHi.sayHi("Bob"));
    }
    
     @Test
    public void testAlice(){
        String expectedResult = "Hello Alice!";
        assertEquals(expectedResult,sayHi.sayHi("Alice"));
    }
     
      @Test
    public void testX(){
        String expectedResult = "Hello X!";
        assertEquals(expectedResult,sayHi.sayHi("X"));
    }
}