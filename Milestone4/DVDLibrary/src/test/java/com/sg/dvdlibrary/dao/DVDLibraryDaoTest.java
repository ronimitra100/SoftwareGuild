package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVDLibrary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DVDLibraryDaoTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    DVDLibraryDao dao = ctx.getBean("dao", DVDLibraryDaoFileImpl.class);

    @Before
    public void setUp() throws Exception {
        List<DVDLibrary> dvdList = dao.getListOfAllDVDs();
        for (DVDLibrary dvd : dvdList) {
            dao.removeDVD(dvd.getTitle());
        }
    }

    @Test
    public void testAddAndGetDVD() throws Exception {
        DVDLibrary dvd1 = new DVDLibrary("Lion King");
        dvd1.setReleaseDate("1994");
        dvd1.setMpaaRating("9.75");
        dvd1.setDirector("Rob Minkoff");
        dvd1.setStudio("Disney");
        dvd1.setUserRating("9.8");
        dao.addDVD("Lion King", dvd1);

        DVDLibrary fromDao = dao.getDVDDetails("Lion King");

        assertEquals(dvd1, fromDao);
    }

    @Test
    public void testRemoveDVD() throws Exception {
        DVDLibrary dvd1 = new DVDLibrary("Lion King");
        dvd1.setReleaseDate("1994");
        dvd1.setMpaaRating("9.75");
        dvd1.setDirector("Rob Minkoff");
        dvd1.setStudio("Disney");
        dvd1.setUserRating("9.8");
        dao.addDVD("Lion King", dvd1);

        DVDLibrary dvd2 = new DVDLibrary("The Kingdom of Solomon");
        dvd2.setReleaseDate("2010");
        dvd2.setMpaaRating("6.6");
        dvd2.setDirector("Shahriar Bahrani");
        dvd2.setStudio("KPS film");
        dvd2.setUserRating("9.2");
        dao.addDVD("The Kingdom of Solomon", dvd2);

        DVDLibrary dvd3 = new DVDLibrary("Jurassic Park");
        dvd3.setReleaseDate("1993");
        dvd3.setMpaaRating("8.1");
        dvd3.setDirector("Steven Spielberg");
        dvd3.setStudio("Universal Studio");
        dvd3.setUserRating("8.7");
        dao.addDVD("Jurassic Park", dvd3);

        assertEquals(3, dao.getListOfAllDVDs().size());

        dao.removeDVD("Lion King");
        assertEquals(2, dao.getListOfAllDVDs().size());
        assertNull(dao.getDVDDetails("Lion King"));

        dao.removeDVD("The Kingdom of Solomon");
        assertEquals(1, dao.getListOfAllDVDs().size());
        assertNull(dao.getDVDDetails("The Kingdom of Solomon"));

        dao.removeDVD("Jurassic Park");
        assertEquals(0, dao.getListOfAllDVDs().size());
        assertNull(dao.getDVDDetails("Jurassic Park"));
    }

    @Test
    public void testUpdateDVD() throws Exception {
        DVDLibrary dvd1 = new DVDLibrary("Lion King");
        dvd1.setReleaseDate("1994");
        dvd1.setMpaaRating("9.75");
        dvd1.setDirector("Rob Minkoff");
        dvd1.setStudio("Disney");
        dvd1.setUserRating("9.8");
        dao.addDVD("Lion King", dvd1);

        DVDLibrary dvd2 = new DVDLibrary("The Kingdom of Solomon");
        dvd2.setReleaseDate("2010");
        dvd2.setMpaaRating("6.6");
        dvd2.setDirector("Shahriar Bahrani");
        dvd2.setStudio("KPS film");
        dvd2.setUserRating("9.2");
        dao.addDVD("The Kingdom of Solomon", dvd2);

        DVDLibrary dvd3 = new DVDLibrary("Jurassic Park");
        dvd3.setReleaseDate("1993");
        dvd3.setMpaaRating("8.1");
        dvd3.setDirector("Steven Spielberg");
        dvd3.setStudio("Universal Studio");
        dvd3.setUserRating("8.7");
        dao.addDVD("Jurassic Park", dvd3);

        DVDLibrary dvd1Updated = new DVDLibrary("Lion King");
        dvd1Updated.setReleaseDate("1990");
        dvd1Updated.setMpaaRating("9.00");
        dvd1Updated.setDirector("Rob");
        dvd1Updated.setStudio("Disney Motion Pictures");
        dvd1Updated.setUserRating("8.50");
        DVDLibrary updatedDVD = dao.updateDVD("Lion King", dvd1Updated);

        DVDLibrary fromDao = dao.getDVDDetails(updatedDVD.getTitle());

        assertEquals(dvd1Updated, fromDao);
    }

    @Test
    public void testGetDVDDetails() throws Exception {

    }

    @Test
    public void testGetListOfAllDVDs() throws Exception {
        DVDLibrary dvd1 = new DVDLibrary("Lion King");
        dvd1.setReleaseDate("1994");
        dvd1.setMpaaRating("9.75");
        dvd1.setDirector("Rob Minkoff");
        dvd1.setStudio("Disney");
        dvd1.setUserRating("9.8");
        dao.addDVD("Lion King", dvd1);

        DVDLibrary dvd2 = new DVDLibrary("The Kingdom of Solomon");
        dvd2.setReleaseDate("2010");
        dvd2.setMpaaRating("6.6");
        dvd2.setDirector("Shahriar Bahrani");
        dvd2.setStudio("KPS film");
        dvd2.setUserRating("9.2");
        dao.addDVD("The Kingdom of Solomon", dvd2);

        DVDLibrary dvd3 = new DVDLibrary("Jurassic Park");
        dvd3.setReleaseDate("1993");
        dvd3.setMpaaRating("8.1");
        dvd3.setDirector("Steven Spielberg");
        dvd3.setStudio("Universal Studio");
        dvd3.setUserRating("8.7");
        dao.addDVD("Jurassic Park", dvd3);

        assertEquals(3, dao.getListOfAllDVDs().size());

    }

    @Test
    public void testGetMatchingDVDs() throws Exception {
        DVDLibrary dvd1 = new DVDLibrary("Lion King");
        dvd1.setReleaseDate("1994");
        dvd1.setMpaaRating("9.75");
        dvd1.setDirector("Rob Minkoff");
        dvd1.setStudio("Disney");
        dvd1.setUserRating("9.8");
        dao.addDVD("Lion King", dvd1);

        DVDLibrary dvd2 = new DVDLibrary("The Kingdom of Solomon");
        dvd2.setReleaseDate("2010");
        dvd2.setMpaaRating("6.6");
        dvd2.setDirector("Shahriar Bahrani");
        dvd2.setStudio("KPS film");
        dvd2.setUserRating("9.2");
        dao.addDVD("The Kingdom of Solomon", dvd2);

        DVDLibrary dvd3 = new DVDLibrary("Jurassic Park");
        dvd3.setReleaseDate("1993");
        dvd3.setMpaaRating("8.1");
        dvd3.setDirector("Steven Spielberg");
        dvd3.setStudio("Universal Studio");
        dvd3.setUserRating("8.7");
        dao.addDVD("Jurassic Park", dvd3);

        List<DVDLibrary> actualMatchingDVDs = dao.getMatchingDVDs("King");
        List<DVDLibrary> expectedMatchingDVDs = Arrays.asList(dvd1, dvd2);

        assertEquals(expectedMatchingDVDs, actualMatchingDVDs);

    }

}
