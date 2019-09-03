package com.sg.functionalunittests;

import com.sg.functionalunittests.MakeTags;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MakeTagsTest {
    private MakeTags makeTags = new MakeTags();
    // The web is built with HTML Strings like "<i>Yay</i>" which 
    // draws Yay as italic text. In this example, the "i" tag makes 
    // <i> and </i> which surround the word "Yay". Given tag and word 
    // Strings, create the HTML String with tags around the word, e.g. 
    // “<i>Yay</i>". 
    //
    // makeTags("i", "Yay") -> "<i>Yay</i>"
    // makeTags("i", "Hello") -> "<i>Hello</i>"
    // makeTags("cite", "Yay") -> "<cite>Yay</cite>"
    /*public String makeTags(String tag, String content) {
        throw new UnsupportedOperationException("Not implemented");
    }*/
    
    // Verify: makeTags("i", "Yay") -> "<i>Yay</i>"
    @Test
    public void testTagiAndYayContent(){
        assertEquals("<i>Yay</i>", makeTags.makeTags("i", "Yay"));
    }
    
    // Verify: makeTags("i", "Hello") -> "<i>Hello</i>"
    @Test
    public void testTagiAndHelloContent(){
        assertEquals("<i>Hello</i>", makeTags.makeTags("i", "Hello"));
    }
    
    // Verify: makeTags("cite", "Yay") -> "<cite>Yay</cite>"
    @Test
    public void testTagciteAndYayContent(){
        assertEquals("<cite>Yay</cite>", makeTags.makeTags("cite", "Yay"));
    }
    
}