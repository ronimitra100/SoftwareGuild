package com.sg.functionalunittests;

import com.sg.functionalunittests.CountXX;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CountXXTest {

    private CountXX countXX = new CountXX();

    // Verify: countXX("abcxx") -> 1
    @Test
    public void testabcxx() {
        assertEquals(1, countXX.countXX("abcxx"));
    }

    // Verify: countXX("xxx") -> 2
    @Test
    public void testxxx() {
        assertEquals(2, countXX.countXX("xxx"));
    }

    // Verify: countXX("xxxx") -> 3
    @Test
    public void testxxxx() {
        assertEquals(3, countXX.countXX("xxxx"));
    }
}