package com.gudangsoal.controller;

import com.gudangsoal.dao.RfNilaiDao;
import com.gudangsoal.model.RfNilai;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller Page /admin/nilai
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/admin/nilai")
public class RfNilaiController extends DefaultController {
    
    private final String activePath = "nilai";
    private final String title = "Pemeliharaan Nilai";
    
    @Autowired
    private RfNilaiDao rfNilaiDao;

    /**
     * Default Controller
     */
    public RfNilaiController() {
        super();
    }
    
    /**
     * Controll form Paramter Nilai
     * 
     * @param model
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView nilai(
            ModelAndView model,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                List<RfNilai> listRfNilai = this.rfNilaiDao.getAll();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("listRfNilai", listRfNilai);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll simpan Paramter Nilai
     * 
     * @param model
     * @param rfNilai
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST)
    public ModelAndView nilai(
            ModelAndView model,
            @ModelAttribute RfNilai rfNilai,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfNilai rfNilaiNew = this.rfNilaiDao.getById(rfNilai.getId());
                rfNilaiNew.merge(rfNilai);
                rfNilaiDao.save(rfNilaiNew);
                List<RfNilai> listRfNilai = this.rfNilaiDao.getAll();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("listRfNilai", listRfNilai);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
}
