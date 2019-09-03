package com.sg.functionalunittests;

import com.sg.functionalunittests.MakePi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class MakePiTest {
    private MakePi makePi = new MakePi();
    
    // Verify: makePi(3) -> {3, 1, 4}
    @Test
    public void test3(){
        int[] arr = {3, 1, 4};
        assertTrue(Arrays.equals(arr, makePi.makePi(3)));
    }
}