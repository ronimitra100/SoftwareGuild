/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.DVD;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DVDLibraryDaoTest {

    private DVDLibraryDao dao;

    public DVDLibraryDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DVDLibraryDao.class);
        List<DVD> dvds = dao.getAllDvds();
        for (DVD currentDvd : dvds) {
            dao.removeDvd(currentDvd.getDvdId());
        }
    }

    @Test
    public void testAddGetDeleteContact() {
        DVD dvd = new DVD();
        dvd.setDvdTitle("Lion King");
        dvd.setDirector("Roger & Rob");
        dvd.setRating("9.8");
        dvd.setReleaseYear("1994");
        dvd.setNotes("One of Roni's favorite movies");
        
        dao.addDvd(dvd);
        DVD fromDb = dao.getDvdById(dvd.getDvdId());
        assertEquals(fromDb, dvd);

        dao.removeDvd(dvd.getDvdId());
        assertNull(dao.getDvdById(dvd.getDvdId()));
    }

    
}
