package com.jmp.controller;

import com.jmp.dao.UserDao;
import com.jmp.entity.User;
import com.jmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Ales on 09.05.2017.
 */
@Controller
@RequestMapping("/")
public class FrontController {

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ModelAndView teacher_page() {
        ModelAndView model = new ModelAndView();
        model.setViewName("teacher");
        return model;
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView student_page() {
        ModelAndView model = new ModelAndView();
        model.setViewName("student");
        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
