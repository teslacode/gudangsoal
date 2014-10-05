/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gudangsoal.controller;

import com.gudangsoal.dao.RfKelasDao;
import com.gudangsoal.dao.RfTingkatDao;
import com.gudangsoal.model.RfKelas;
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
@RequestMapping("/admin/kelas")
public class RfKelasController extends DefaultController {
 
    private String activePath = "kelas";
    private String title = "Pemeliharaan Kelas";
    
//    //Model
//    private RfKelas rfKelas;
    
    //Parameter
    private List<RfTingkat> listRfTingkat;
    
    //DAO
    @Autowired
    private RfKelasDao rfKelasDao;
    @Autowired
    private RfTingkatDao rfTingkatDao;
    
    public RfKelasController(){
        super();
    }
    
    /**
     * Controll form Paramter Kelas
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView kelas(
            ModelAndView model,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfKelas rfKelas = new RfKelas();
                List<RfKelas> listRfKelas = this.rfKelasDao.getAll();
                this.listRfTingkat = this.rfTingkatDao.getAll();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfKelas", rfKelas);
                model.addObject("listRfKelas", listRfKelas);
                model.addObject("listRfTingkat", this.listRfTingkat);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll simpan Paramter Kelas
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method= RequestMethod.POST, params={"save"})
    public ModelAndView kelas(
            ModelAndView model,
            @ModelAttribute("post") RfKelas rfKelas,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfKelas rfKelasNew = new RfKelas();
                rfKelasNew.merge(rfKelas);
                this.rfKelasDao.save(rfKelasNew);
                List<RfKelas> listRfKelas = this.rfKelasDao.getAll();
                rfKelas = new RfKelas();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfKelas", rfKelas);
                model.addObject("listRfKelas", listRfKelas);
                model.addObject("listRfTingkat", this.listRfTingkat);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll hapus Paramter Kelas
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method= RequestMethod.POST, params={"hapus"})
    public ModelAndView kelas(
            ModelAndView model,
            @RequestParam(value="id") String id,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfKelas rfKelas = this.rfKelasDao.getById(id);
                this.rfKelasDao.delete(rfKelas);
                List<RfKelas> listRfKelas = this.rfKelasDao.getAll();
                rfKelas = new RfKelas();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfKelas", rfKelas);
                model.addObject("listRfKelas", listRfKelas);
                model.addObject("listRfTingkat", this.listRfTingkat);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
}
