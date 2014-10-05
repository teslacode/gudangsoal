package com.gudangsoal.controller;

import com.gudangsoal.dao.RfTingkatDao;
import com.gudangsoal.model.RfTingkat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/tingkat")
public class RfTingkatController extends DefaultController {
    
    private String activePath = "tingkat";
    private String title = "Pemeliharaan Tingkat";
    
    //private RfTingkat rfTingkat;
    
    @Autowired
    private RfTingkatDao rfTingkatDao;
    
    public RfTingkatController(){
        super();
    }
    
    /**
     * Controll form Paramter Tingkat
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView tingkat(
            ModelAndView model,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfTingkat rfTingkat = new RfTingkat();
                List<RfTingkat> listRfTingkat = this.rfTingkatDao.getAll();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfTingkat", rfTingkat);
                model.addObject("listRfTingkat", listRfTingkat);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll simpan Paramter Tingkat
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method= RequestMethod.POST, params={"save"})
    public ModelAndView tingkat(
            ModelAndView model,
            @ModelAttribute("post") RfTingkat rfTingkat,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfTingkat rfTingkatNew = new RfTingkat();
                rfTingkatNew.merge(rfTingkat);
                this.rfTingkatDao.save(rfTingkatNew);
                List<RfTingkat> listRfTingkat = this.rfTingkatDao.getAll();
                rfTingkat = new RfTingkat();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfTingkat", rfTingkat);
                model.addObject("listRfTingkat", listRfTingkat);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll hapus Paramter Tingkat
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method= RequestMethod.POST, params={"hapus"})
    public ModelAndView tingkat(
            ModelAndView model,
            @RequestParam(value="id") String id,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfTingkat rfTingkat = this.rfTingkatDao.getById(id);
                this.rfTingkatDao.delete(rfTingkat);
                List<RfTingkat> listRfTingkat = this.rfTingkatDao.getAll();
                rfTingkat = new RfTingkat();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfTingkat", rfTingkat);
                model.addObject("listRfTingkat", listRfTingkat);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
}
