package com.gudangsoal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController extends DefaultController {
    
    private String activePath = "admin";
    
    public AdminController(){
        super();
    }
    
    /**
     * Controll halaman admin
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView index(
            ModelAndView model,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                model.setViewName("admin/index");
                model.addObject("title", "Administration");
                model.addObject("activePath", this.activePath);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
}
