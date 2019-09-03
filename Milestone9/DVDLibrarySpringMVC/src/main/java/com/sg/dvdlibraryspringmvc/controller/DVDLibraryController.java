/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DVDLibraryDao;
import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.model.DVD;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DVDLibraryController {

    DVDLibraryDao dao;

    @Inject
    public DVDLibraryController(DVDLibraryDao dao) {
        this.dao = dao;
    }

    @PostConstruct
    public void init() {
        dao.getAllDvds();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model) {
        List<DVD> dvdList = dao.getAllDvds();

        model.addAttribute("dvdList", dvdList);

        return "index";
    }

    @RequestMapping(value = "/displayDvdsPage", method = RequestMethod.GET)
    public String displayDvdsPage(Model model) {
        List<DVD> dvdList = dao.getAllDvds();
        model.addAttribute("dvdList", dvdList);
        return "index";
    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    public String createDvd(HttpServletRequest request) {
        DVD dvd = new DVD();
        dvd.setDvdTitle(request.getParameter("dvdTitle"));
        dvd.setDirector(request.getParameter("director"));
        String releaseYear = (request.getParameter("releaseYear"));
        dvd.setReleaseYear(releaseYear);
        dvd.setRating(request.getParameter("rating"));
        dvd.setNotes(request.getParameter("notes"));
        dao.addDvd(dvd);
        return "redirect:displayDvdsPage";
        //return "redirect:index";
    }

    @RequestMapping(value = "/displayDvdDetails", method = RequestMethod.GET)
    public String displayDvdDetails(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        Integer dvdId = Integer.parseInt(dvdIdParameter);
        DVD dvd = dao.getDvdById(dvdId);
        model.addAttribute("dvd", dvd);
        return "dvdDetails";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {
        String dvdIdParameter = request.getParameter("dvdId");
        Integer dvdId = Integer.parseInt(dvdIdParameter);
        dao.removeDvd(dvdId);
        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayEditDvdForm", method = RequestMethod.GET)
    public String displayEditDvdForm(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        Integer dvdId = Integer.parseInt(dvdIdParameter);
        DVD dvd = dao.getDvdById(dvdId);
        model.addAttribute("dvd", dvd);
        return "editDvdForm";
    }

    @RequestMapping(value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") DVD dvd, BindingResult result) {
        if (result.hasErrors()) {
            return "editDvdForm";
        }
        dao.updateDvd(dvd);

        return "redirect:displayDvdsPage";
    }

}
