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
public class GreatPartyTest {

    GreatParty party = new GreatParty();

    public GreatPartyTest() {
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

    // When squirrels get together for a party, they like to have cigars.
// A squirrel party is successful when the number of cigars is between
// 40 and 60, inclusive. Unless it is the weekend, in which case there 
// is no upper bound on the number of cigars. Return true if the party
// with the given values is successful, or false otherwise.
//
// greatParty(30, false) → false
// greatParty(50, false) → true
// greatParty(70, true) → true
//============================
    // greatParty(40, true) → true
// greatParty(40, false) → true
// greatParty(60, true) → true
//greatParty(60, false) → true
 //greatParty(61, false) → false
    //greatParty(61, true) → true
     // greatParty(39, true) → false
// greatParty(39, false) → false
   @Test
   public void test40True(){
       assertTrue(party.greatParty(40, true));
   }
   
   @Test
   public void test40TFalse(){
       assertTrue(party.greatParty(40, false));
   }
   
    @Test
   public void test60True(){
       assertTrue(party.greatParty(60, true));
   }
   
   @Test
   public void test60TFalse(){
       assertTrue(party.greatParty(60, false));
   }
   
    @Test
   public void test39True(){
       assertFalse(party.greatParty(39, true));
   }
   
   @Test
   public void test39TFalse(){
       assertFalse(party.greatParty(39, false));
   }
   
     @Test
   public void test61True(){
       assertTrue(party.greatParty(61, true));
   }
   
   @Test
   public void test61TFalse(){
       assertFalse(party.greatParty(61, false));
   }

    @Test
    public void test30False() {
        assertFalse(party.greatParty(30, false));
    }

    @Test
    public void tes50TFalse() {
        assertTrue(party.greatParty(50, false));
    }

    @Test
    public void tes70True() {
        assertTrue(party.greatParty(70, true));
    }
}