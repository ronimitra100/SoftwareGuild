package com.sg.contactlistspringmvc;
import com.sg.contactlistspringmvc.dao.ContactListDao;
import com.sg.contactlistspringmvc.model.Contact;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@CrossOrigin
@Controller
public class RESTController {
    private ContactListDao dao;
    
    @Inject
    public RESTController(ContactListDao dao){
        this.dao = dao;
    }
    
    @RequestMapping(value="/contact/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Contact getContact(@PathVariable("id") long id){
        return dao.getContactById(id);
    }
    
    @RequestMapping(value="/contact",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Contact createContact(@Valid @RequestBody Contact contact){
        return dao.addContact(contact);
    }
    
    @RequestMapping(value="/contact/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteContact(@PathVariable("id") long id){
        dao.removeContact(id);
    }
    
    @RequestMapping(value="/contact/{id}",method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void updateContact(@PathVariable("id") long id, @Valid @RequestBody Contact contact) throws UpdateIntegrityException{
        //contact.setContactId(id);
        if (id != contact.getContactId()){
            throw new UpdateIntegrityException("Contact Id must match Contact Id in submitted data.");
        }
        dao.updateContact(contact);
    }
    
     @RequestMapping(value="/contacts",method=RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts(){
        return dao.getAllContacts();
    }

}
