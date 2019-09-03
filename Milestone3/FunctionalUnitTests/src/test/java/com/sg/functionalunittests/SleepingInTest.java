package com.sg.functionalunittests;

import com.sg.functionalunittests.SleepingIn;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SleepingInTest {

    private SleepingIn sleepingIn = new SleepingIn();

    // Verify: canSleepIn(false, false) -> true
    @Test
    public void testNotWeekdayAndNotVacation() {
        assertTrue(sleepingIn.canSleepIn(false, false));
    }

    // Verify: canSleepIn(true, false) -> false
    @Test
    public void testWeekdayAndNotVacation() {
        assertFalse(sleepingIn.canSleepIn(true, false));
    }

    // Verify: canSleepIn(false, true) -> true
    @Test
    public void testNotWeekdayAndVacation() {
        assertTrue(sleepingIn.canSleepIn(false, true));
    }
}