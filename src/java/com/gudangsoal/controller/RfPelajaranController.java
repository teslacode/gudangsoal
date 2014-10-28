package com.gudangsoal.controller;

import com.gudangsoal.dao.RfKelasDao;
import com.gudangsoal.dao.RfPelajaranDao;
import com.gudangsoal.dao.RfTingkatDao;
import com.gudangsoal.model.RfKelas;
import com.gudangsoal.model.RfPelajaran;
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

/**
 * Controller page /admin/pelajaran
 * 
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/admin/pelajaran")
public class RfPelajaranController extends DefaultController {
 
    private final String activePath = "pelajaran";
    private final String title = "Pemeliharaan Pelajaran";
    
    //Parameter
    private List<RfTingkat> listRfTingkat;
    private List<RfKelas> listRfKelas;
    
    //DAO
    @Autowired
    private RfPelajaranDao rfPelajaranDao;
    @Autowired
    private RfTingkatDao rfTingkatDao;
    @Autowired
    private RfKelasDao rfKelasDao;
    
    /**
     * Default Controller
     */
    public RfPelajaranController(){
        super();
    }
    
    /**
     * Controll form Paramter Pelajaran
     * 
     * @param model
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView pelajaran(
            ModelAndView model,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfPelajaran rfPelajaran = new RfPelajaran();
                List<RfPelajaran> listRfPelajaran = this.rfPelajaranDao.getAll();
                this.listRfTingkat = this.rfTingkatDao.getAll();
                this.listRfKelas = this.rfKelasDao.getAll();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfPelajaran", rfPelajaran);
                model.addObject("listRfPelajaran", listRfPelajaran);
                model.addObject("listRfTingkat", this.listRfTingkat);
                model.addObject("listRfKelas", this.listRfKelas);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll simpan Paramter Pelajaran
     * 
     * @param model
     * @param rfPelajaran
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST, params={"save"})
    public ModelAndView pelajaran(
            ModelAndView model,
            @ModelAttribute("post") RfPelajaran rfPelajaran,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfPelajaran rfPelajaranNew = new RfPelajaran();
                rfPelajaranNew.merge(rfPelajaran);
                this.rfPelajaranDao.save(rfPelajaranNew);
                List<RfPelajaran> listRfPelajaran = this.rfPelajaranDao.getAll();
                rfPelajaran = new RfPelajaran();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfPelajaran", rfPelajaran);
                model.addObject("listRfPelajaran", listRfPelajaran);
                model.addObject("listRfTingkat", this.listRfTingkat);
                model.addObject("listRfKelas", this.listRfKelas);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll hapus Paramter Pelajaran
     * 
     * @param model
     * @param id
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST, params={"hapus"})
    public ModelAndView pelajaran(
            ModelAndView model,
            @RequestParam(value="id") String id,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                RfPelajaran rfPelajaran = this.rfPelajaranDao.getById(id);
                this.rfPelajaranDao.delete(rfPelajaran);
                List<RfPelajaran> listRfPelajaran = this.rfPelajaranDao.getAll();
                rfPelajaran = new RfPelajaran();
                
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("rfPelajaran", rfPelajaran);
                model.addObject("listRfPelajaran", listRfPelajaran);
                model.addObject("listRfKelas", this.listRfKelas);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
}
