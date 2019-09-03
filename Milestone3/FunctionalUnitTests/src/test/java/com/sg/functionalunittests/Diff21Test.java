package com.sg.functionalunittests;

import com.sg.functionalunittests.Diff21;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Diff21Test {

    private Diff21 diff21 = new Diff21();

    // Verify: diff21(23) -> 4
    @Test
    public void test23() {
        assertEquals(4, diff21.diff21(23));
    }

    // Verify: diff21(10) -> 11
    @Test
    public void test10() {
        assertEquals(11, diff21.diff21(10));
    }

    // Verify: diff21(21) -> 0
    @Test
    public void test21() {
        assertEquals(0, diff21.diff21(21));
    }
}