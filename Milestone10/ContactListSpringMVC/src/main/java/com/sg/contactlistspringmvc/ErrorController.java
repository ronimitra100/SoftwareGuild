package com.sg.contactlistspringmvc;
import com.sg.contactlistspringmvc.dao.*;
import com.sg.contactlistspringmvc.model.*;
import java.text.MessageFormat;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
    
    @RequestMapping(value="/error")
    public String customError(HttpServletRequest request, HttpServletResponse response, Model model){
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        Throwable throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
        String exceptionMessage = null;
        String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri== null){
            requestUri = "Unknown";
        }
        String message = MessageFormat.format("{0} returned from {1}:{2}", statusCode, requestUri, exceptionMessage);
        model.addAttribute("errorMessage", message);
        return "customError";
    }
}
