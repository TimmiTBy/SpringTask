package com.jmp.controller;

import com.jmp.model.FileBucket;
import com.jmp.model.User;
import com.jmp.model.UserDocument;
import com.jmp.service.UserDocumentService;
import com.jmp.service.UserService;
import com.jmp.utils.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ales on 09.05.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    UserService userService;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ModelAndView teacher_page() {
        ModelAndView model = new ModelAndView();
        User user = userService.findUserByName(getPrincipal());
        model.addObject("user", user);
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


    @RequestMapping(value = {"/add-document-{userName}"}, method = RequestMethod.GET)
    public ModelAndView addDocuments(@PathVariable String userName) {
        User user = userService.findUserByName(userName);
        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        FileBucket fileModel = new FileBucket();
        model.addObject("fileBucket", fileModel);
        List<UserDocument> documents = userDocumentService.findAllByUserId(user.getId());
        model.addObject("documents", documents);
        model.setViewName("managedocuments");
        return model;
    }

    @RequestMapping(value = {"/add-document-{userName}"}, method = RequestMethod.POST)
    public ModelAndView uploadDocument(@Valid FileBucket fileBucket, BindingResult result, @PathVariable String userName) throws IOException {
        ModelAndView model = new ModelAndView();
        User user = userService.findUserByName(userName);
        model.addObject("user", user);
        if (result.hasErrors()) {
            List<UserDocument> documents = userDocumentService.findAllByUserId(user.getId());
            model.addObject("documents", documents);
            model.setViewName("managedocuments");
            return model;
        } else {
            saveDocument(fileBucket, user);
            model.setViewName("redirect:/add-document-" + userName);
            return model;
        }
    }

    @RequestMapping(value = { "/download-document-{userName}-{docId}" }, method = RequestMethod.GET)
    public ModelAndView downloadDocument(@PathVariable String userName, @PathVariable int docId, HttpServletResponse response) throws IOException {
        UserDocument document = userDocumentService.findById(docId);
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/add-document-" + userName);
        return model;
    }

    private void saveDocument(FileBucket fileBucket, User user) throws IOException{
        UserDocument document = new UserDocument();
        MultipartFile multipartFile = fileBucket.getFile();
        document.setName(multipartFile.getOriginalFilename());
        document.setContent(multipartFile.getBytes());
        document.setUser(user);
        userDocumentService.saveDocument(document);
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
