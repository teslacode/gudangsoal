package com.gudangsoal.controller;

import com.gudangsoal.dao.HasilPertanyaanDao;
import com.gudangsoal.dao.PertanyaanDao;
import com.gudangsoal.dao.PilihanDao;
import com.gudangsoal.dao.RfKelasDao;
import com.gudangsoal.dao.RfNilaiDao;
import com.gudangsoal.dao.RfPelajaranDao;
import com.gudangsoal.dao.RfTingkatDao;
import com.gudangsoal.model.HasilPertanyaan;
import com.gudangsoal.model.Pertanyaan;
import com.gudangsoal.model.Pilihan;
import com.gudangsoal.model.RfKelas;
import com.gudangsoal.model.RfNilai;
import com.gudangsoal.model.RfPelajaran;
import com.gudangsoal.model.RfTingkat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController extends DefaultController {
    
//    private List<Pertanyaan> listPertanyaan;
//    private List<RfNilai> listRfNilai;
    
    @Autowired
    private RfTingkatDao rfTingkatDao;
    @Autowired
    private RfKelasDao rfKelasDao;
    @Autowired
    private RfPelajaranDao rfPelajaranDao;
    @Autowired
    private RfNilaiDao rfNilaiDao;
    @Autowired
    private PertanyaanDao pertanyaanDao;
    @Autowired
    private HasilPertanyaanDao hasilPertanyaanDao;
    @Autowired
    private PilihanDao pilihanDao;
    
    /**
     * Constructor
     */
    public IndexController(){
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
    public ModelAndView index(
            ModelAndView model,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            this.setAll(model, request, response);
            List<RfTingkat> listRfTingkat = rfTingkatDao.getAll();

            model.addObject("title", "Gudang Soal");
            model.addObject("listRfTingkat", listRfTingkat);
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
    public ModelAndView index(
            ModelAndView model,
            @PathVariable String tingkatId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            this.setAll(model, request, response);
            List<RfKelas> listRfKelas = rfKelasDao.getAll(tingkatId);

            model.setViewName("index");
            model.addObject("title", "Gudang Soal");
            model.addObject("listRfKelas", listRfKelas);
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
    public ModelAndView index(
            ModelAndView model,
            @PathVariable String tingkatId,
            @PathVariable String kelasId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            this.setAll(model, request, response);
            List<RfPelajaran> listRfPelajaran = rfPelajaranDao.getAll(tingkatId, kelasId);

            model.setViewName("index");
            model.addObject("title", "Gudang Soal");
            model.addObject("listRfPelajaran", listRfPelajaran);
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
    public ModelAndView index(
            ModelAndView model,
            @PathVariable String tingkatId,
            @PathVariable String kelasId,
            @PathVariable String pelajaranId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            this.setAll(model, request, response);
            List<Pertanyaan> listPertanyaan = pertanyaanDao.generate(tingkatId, kelasId, pelajaranId);
            List<RfNilai> listRfNilai = rfNilaiDao.getAll();
            
            setSession(request, "listPertanyaanTest", listPertanyaan);
            setSession(request, "listRfNilaiTest", listRfNilai);

            model.setViewName("index");
            model.addObject("title", "Gudang Soal");
            model.addObject("listPertanyaan", listPertanyaan);
            model.addObject("listRfNilai", listRfNilai);
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
    @RequestMapping(value="/{tingkatId}/{kelasId}/{pelajaranId}", method = RequestMethod.POST)
    public ModelAndView indexPost(
            ModelAndView model,
            @PathVariable String tingkatId,
            @PathVariable String kelasId,
            @PathVariable String pelajaranId,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        try{
            this.setAll(model, request, response);
            Pilihan pilihan;
            Integer i = 0;
            Integer total = 0;
            
            List<Pertanyaan> listPertanyaan = (List<Pertanyaan>) getSession(request, "listPertanyaanTest");
            List<RfNilai> listRfNilai = (List<RfNilai>) getSession(request, "listRfNilaiTest");
            
            for(Pertanyaan pertanyaan : listPertanyaan){
                String pilihanId = request.getParameter("pertanyaan"+pertanyaan.getId().toString());
                HasilPertanyaan hasilPertanyaan = new HasilPertanyaan();
                hasilPertanyaan.setPertanyaanId(pertanyaan.getId());
                
                if(pilihanId != null){
                    pilihan = pilihanDao.getById(Long.parseLong(pilihanId));
                    pertanyaan.setPilihanId(pilihan.getId());
                    pertanyaan.setHasil(pilihan.getIsValid());
                    hasilPertanyaan.setHasil(pilihan.getIsValid());
                }
                
                RfNilai rfNilai = rfNilaiDao.getByHasil(pertanyaan.getHasil());
                pertanyaan.setNilai(rfNilai.getNilai());
                total += rfNilai.getNilai();
                
                hasilPertanyaanDao.save(hasilPertanyaan);
                listPertanyaan.set(i, pertanyaan);
                i++;
            }

            model.setViewName("index");
            model.addObject("title", "Gudang Soal");
            model.addObject("listPertanyaan", listPertanyaan);
            model.addObject("listRfNilai", listRfNilai);
            model.addObject("total", total);
        }catch(Exception e){
            this.setError(model, e);
        }
        return model;
    }
    
}
