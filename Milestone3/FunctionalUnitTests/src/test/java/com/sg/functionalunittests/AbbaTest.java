package com.sg.functionalunittests;

import com.sg.functionalunittests.Abba;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbbaTest {
  private Abba abba = new Abba();
  
  // Verify: abba("Hi", "Bye") -> "HiByeByeHi"
  @Test
  public void testAbbaForHiBye(){
      String expectedResult = "HiByeByeHi";
      assertEquals(expectedResult, abba.abba("Hi", "Bye"));
  }
  
// Verify: abba("Yo", "Alice") -> "YoAliceAliceYo"
  public void testAbbaForYoAlice(){
      String expectedResult = "YoAliceAliceYo";
      assertEquals(expectedResult, abba.abba("Yo", "Alice"));
  }
  
// Verify: abba("What", "Up") -> "WhatUpUpWhat"
  public void testAbbaForWhatUp(){
      String expectedResult = "WhatUpUpWhat";
      assertEquals(expectedResult, abba.abba("What", "Up"));
  }
}