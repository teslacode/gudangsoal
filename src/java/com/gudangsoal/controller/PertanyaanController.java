package com.gudangsoal.controller;

import com.gudangsoal.dao.PertanyaanDao;
import com.gudangsoal.dao.RfKelasDao;
import com.gudangsoal.dao.RfPelajaranDao;
import com.gudangsoal.dao.RfTingkatDao;
import com.gudangsoal.model.Pertanyaan;
import com.gudangsoal.model.RfKelas;
import com.gudangsoal.model.RfPelajaran;
import com.gudangsoal.model.RfTingkat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/soal")
public class PertanyaanController extends DefaultController {
    
    private String activePath = "soal";
    private String title = "Pemeliharaan Soal";
    
//    private List<Pertanyaan> listPertanyaan;
//    
//    private Pertanyaan pertanyaan;
    
    @Autowired
    private RfTingkatDao rfTingkatDao;
    @Autowired
    private RfKelasDao rfKelasDao;
    @Autowired
    private RfPelajaranDao rfPelajaranDao;
    @Autowired
    private PertanyaanDao pertanyaanDao;
    
    public PertanyaanController(){
        super();
    }
    
    /**
     * Control load awal
     * 
     * @param model
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView soal(
            ModelAndView model,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                List<RfTingkat> listRfTingkat = rfTingkatDao.getAll();

                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("listRfTingkat", listRfTingkat);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Control load dengan parameter tingkat id
     * 
     * @param model
     * @param tingkatId
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="/{tingkatId}", method = RequestMethod.GET)
    public ModelAndView soal(
            ModelAndView model,
            @PathVariable String tingkatId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                List<RfKelas> listRfKelas = rfKelasDao.getAll(tingkatId);

                model.setViewName("admin/soal");
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("listRfKelas", listRfKelas);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Control load dengan parameter tingkat id & kelas id
     * 
     * @param model
     * @param tingkatId
     * @param kelasId
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="/{tingkatId}/{kelasId}", method = RequestMethod.GET)
    public ModelAndView soal(
            ModelAndView model,
            @PathVariable String tingkatId,
            @PathVariable String kelasId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                List<RfPelajaran> listRfPelajaran = rfPelajaranDao.getAll(tingkatId, kelasId);

                model.setViewName("admin/soal");
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("listRfPelajaran", listRfPelajaran);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Control load dengan parameter tingkat id, kelas id, dan pelajaranId
     * 
     * @param model
     * @param tingkatId
     * @param kelasId
     * @param pelajaranId
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="/{tingkatId}/{kelasId}/{pelajaranId}", method = RequestMethod.GET)
    public ModelAndView soal(
            ModelAndView model,
            @PathVariable String tingkatId,
            @PathVariable String kelasId,
            @PathVariable String pelajaranId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                Pertanyaan pertanyaan = new Pertanyaan(new Date(), tingkatId, kelasId, pelajaranId);
                List<Pertanyaan> listPertanyaan = pertanyaanDao.getAll(tingkatId, kelasId, pelajaranId);

                model.setViewName("admin/soal");
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("pertanyaan", pertanyaan);
                model.addObject("listPertanyaan", listPertanyaan);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll simpan Pertanyaan
     * 
     * @param model
     * @param pertanyaan
     * @param tingkatId
     * @param kelasId
     * @param pelajaranId
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="/{tingkatId}/{kelasId}/{pelajaranId}", method= RequestMethod.POST, params={"save"})
    public ModelAndView soal(
            ModelAndView model,
            @ModelAttribute("post") Pertanyaan pertanyaan,
            @PathVariable String tingkatId,
            @PathVariable String kelasId,
            @PathVariable String pelajaranId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                //this.pertanyaan.merge(pertanyaan);
                this.pertanyaanDao.save(pertanyaan);
                List<Pertanyaan> listPertanyaan = pertanyaanDao.getAll(tingkatId, kelasId, pelajaranId);
                
                model.setViewName("admin/soal");
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("pertanyaan", pertanyaan);
                model.addObject("listPertanyaan", listPertanyaan);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
    /**
     * Controll simpan Pertanyaan
     * 
     * @param model
     * @param pertanyaan
     * @param tingkatId
     * @param kelasId
     * @param pelajaranId
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="/{tingkatId}/{kelasId}/{pelajaranId}", method= RequestMethod.POST, params={"status"})
    @ResponseBody
    public String soalIsActive(
            ModelAndView model,
            @ModelAttribute("post") Pertanyaan pertanyaan,
            @PathVariable String tingkatId,
            @PathVariable String kelasId,
            @PathVariable String pelajaranId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            if(this.check(model, request, response)){
                Pertanyaan pertanyaanOld = this.pertanyaanDao.getById(pertanyaan.getId());
                pertanyaan.setIsActive(!pertanyaanOld.getIsActive());
                this.pertanyaanDao.save(pertanyaan);
                List<Pertanyaan> listPertanyaan = pertanyaanDao.getAll(tingkatId, kelasId, pelajaranId);
                pertanyaan = new Pertanyaan(new Date(), tingkatId, kelasId, pelajaranId);
                
                model.setViewName("admin/soal");
                model.addObject("title", this.title);
                model.addObject("activePath", this.activePath);
                model.addObject("pertanyaan", pertanyaan);
                model.addObject("listPertanyaan", listPertanyaan);
            }
        }catch(Exception e){
            this.setError(model, e);
        }
        return pertanyaan.getStatus();
    }
    
}
