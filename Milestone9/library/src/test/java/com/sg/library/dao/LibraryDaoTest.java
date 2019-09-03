/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.library.dao;

import com.sg.library.model.Author;
import com.sg.library.model.Book;
import com.sg.library.model.Publisher;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;

public class LibraryDaoTest {
    LibraryDao dao;

    public LibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("libraryDao", LibraryDao.class);
        
        List<Book> books = dao.getAllBooks();
        for (Book currentBook : books){
            dao.deleteBook(currentBook.getBookId());
        }
        
        List<Author> authors = dao.getAllAuthors();
        for (Author currentAuthor : authors){
            dao.deleteAuthor(currentAuthor.getAuthorId());
        }
        
        List<Publisher> publishers = dao.getAllPublishers();
        for (Publisher currentPublisher : publishers){
            dao.deletePublisher(currentPublisher.getPublisherId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addAuthor method, of class LibraryDao.
     */
    
    @Test
    public void addGetPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");
        
        dao.addPublisher(publisher);
        
        Publisher fromDao = dao.getPublisherById(publisher.getPublisherId());
        assertEquals(fromDao,publisher);
    }
    
    @Test
    public void deletePublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");
        
        dao.addPublisher(publisher);
        
        Publisher fromDao = dao.getPublisherById(publisher.getPublisherId());
        assertEquals(fromDao,publisher);
        dao.deletePublisher(publisher.getPublisherId());
        assertNull(dao.getPublisherById(publisher.getPublisherId()));
    }
    
    @Test
    public void addGetAuthor(){
        Author author = new Author();
       
        author.setFirstName("Author");
        author.setLastName("Test");
        author.setStreet("49 Oak Street");
        author.setCity("Author Town");
        author.setState("OH");
        author.setZip("44398");
        author.setPhone("555-1234");
        
        dao.addAuthor(author);
        Author fromDao = dao.getAuthorById(author.getAuthorId());
        assertEquals(fromDao, author);
        
    }
    
     @Test
    public void deleteAuthor(){
        Author author = new Author();
       
        author.setFirstName("Author");
        author.setLastName("Test");
        author.setStreet("49 Oak Street");
        author.setCity("Author Town");
        author.setState("OH");
        author.setZip("44398");
        author.setPhone("555-1234");
        
        dao.addAuthor(author);
        Author fromDao = dao.getAuthorById(author.getAuthorId());
        assertEquals(fromDao, author);
        dao.deleteAuthor(author.getAuthorId());
        assertNull(dao.getAuthorById(author.getAuthorId()));
        
    }
    
    @Test
    public void addGetBook(){
        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");
        dao.addPublisher(publisher);
        
        Author author = new Author();      
        author.setFirstName("Author");
        author.setLastName("Test");
        author.setStreet("49 Oak Street");
        author.setCity("Author Town");
        author.setState("OH");
        author.setZip("44398");
        author.setPhone("555-1234");
        dao.addAuthor(author);
        
        Book b = new Book();
        b.setTitle("Great Book");
        b.setIsbn("12345");
        b.setPrice(new BigDecimal("12.95"));
        b.setPublishDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        b.setPublisher(publisher);
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        b.setAuthors(authors);
        dao.addBook(b);
        
        Book fromDao = dao.getBookById(b.getBookId());
        assertEquals(fromDao, b);
    }
    
    @Test
    public void deleteBook(){
        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");
        dao.addPublisher(publisher);
        
        Author author = new Author();      
        author.setFirstName("Author");
        author.setLastName("Test");
        author.setStreet("49 Oak Street");
        author.setCity("Author Town");
        author.setState("OH");
        author.setZip("44398");
        author.setPhone("555-1234");
        dao.addAuthor(author);
        
        Book b = new Book();
        b.setTitle("Great Book");
        b.setIsbn("12345");
        b.setPrice(new BigDecimal("12.95"));
        b.setPublishDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        b.setPublisher(publisher);
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        b.setAuthors(authors);
        dao.addBook(b);
        
        Book fromDao = dao.getBookById(b.getBookId());
        assertEquals(fromDao, b);
        
        dao.deleteBook(b.getBookId());
        assertNull(dao.getBookById(b.getBookId()));
    }
}