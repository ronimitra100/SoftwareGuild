package com.sg.functionalunittests;

import com.sg.functionalunittests.GreatParty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GreatPartyTest {

    GreatParty party = new GreatParty();

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

    @Test
    public void test40True() {
        assertTrue(party.greatParty(40, true));
    }

    @Test
    public void test40TFalse() {
        assertTrue(party.greatParty(40, false));
    }

    @Test
    public void test60True() {
        assertTrue(party.greatParty(60, true));
    }

    @Test
    public void test60TFalse() {
        assertTrue(party.greatParty(60, false));
    }

    @Test
    public void test39True() {
        assertFalse(party.greatParty(39, true));
    }

    @Test
    public void test39TFalse() {
        assertFalse(party.greatParty(39, false));
    }

    @Test
    public void test61True() {
        assertTrue(party.greatParty(61, true));
    }

    @Test
    public void test61TFalse() {
        assertFalse(party.greatParty(61, false));
    }
}