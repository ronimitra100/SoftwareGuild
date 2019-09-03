package com.sg.functionalunittests;

import com.sg.functionalunittests.FirstLast6;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FirstLast6Test {

    private FirstLast6 firstLast6 = new FirstLast6();

    // Verify: firstLast6({1, 2, 6}) -> true
    @Test
    public void testArrayWith6AsLastElement() {
        int[] arr = {1, 2, 6};
        assertTrue(firstLast6.firstLast6(arr));
    }

    // Verify: firstLast6({6, 1, 2, 3}) -> true
    @Test
    public void testArrayWith6AsFirstElement() {
        int[] arr = {6, 1, 2, 3};
        assertTrue(firstLast6.firstLast6(arr));
    }

    // Verify: firstLast6({13, 6, 1, 2, 3}) -> false
    @Test
    public void testArrayWith6AsNeitherFirstNorLastElement() {
        int[] arr = {13, 6, 1, 2, 3};
        assertFalse(firstLast6.firstLast6(arr));
    }
}