package com.sg.contactlistspringmvc;
import com.sg.contactlistspringmvc.dao.ContactListDao;
import com.sg.contactlistspringmvc.dao.SearchTerm;
import com.sg.contactlistspringmvc.model.Contact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    private ContactListDao dao;
    
    @Inject
    public SearchController(ContactListDao dao){
        this.dao =dao;
    }
    
    @RequestMapping(value="/displaySearchPage", method=RequestMethod.GET)
    public String displaySearchPage(){
        return "search";
    }
    
    @RequestMapping(value="/search/contacts", method=RequestMethod.POST)
    @ResponseBody
    public List<Contact> searchContacts(@RequestBody Map<String,String> searchMap){
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        
        String currentTerm = searchMap.get("firstName");
        if (currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.FIRST_NAME, currentTerm);
        }
        
        currentTerm = searchMap.get("lastName");
        if (currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.LAST_NAME, currentTerm);
        }
        
        currentTerm = searchMap.get("company");
        if (currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.COMPANY, currentTerm);
        }
        
        currentTerm = searchMap.get("email");
        if (currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.EMAIL, currentTerm);
        }
        
        currentTerm = searchMap.get("phone");
        if (currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.PHONE, currentTerm);
        }
        
        return dao.searchContacts(criteriaMap);
        
    }
}
