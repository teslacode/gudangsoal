package com.gudangsoal.controller;

import com.gudangsoal.dao.RfKelasDao;
import com.gudangsoal.dao.RfPelajaranDao;
import com.gudangsoal.dao.RfTingkatDao;
import com.gudangsoal.model.BreadCrumbs;
import com.gudangsoal.model.RfKelas;
import com.gudangsoal.model.RfPelajaran;
import com.gudangsoal.model.RfTingkat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller Default. Sebagai parent semua Controller
 * 
 * @author Ade Fruandta
 */
@Controller
public class DefaultController {

    protected String contextPath;
    protected String contextPathAdmin;
    protected String path;
    protected String fullPath;
    protected List<BreadCrumbs> listBreadCrumbs;
    @Autowired
    private RfTingkatDao rfTingkatDao;
    @Autowired
    private RfKelasDao rfKelasDao;
    @Autowired
    private RfPelajaranDao rfPelajaranDao;

    /**
     * Default Constructor
     */
    public DefaultController() {
        this.listBreadCrumbs = new ArrayList<BreadCrumbs>();
    }

    /**
     * Check semua validasi
     *
     * @param model
     * @param request
     * @param response
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean check(
            ModelAndView model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Boolean result;
        result = true;
        this.setAll(model, request, response);
        return result;
    }

    /**
     * Set semua parameter
     *
     * @param model
     * @param request
     * @param response
     * @throws java.lang.Exception
     */
    public void setAll(
            ModelAndView model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        this.generateLink(model, request, response);
        this.generateBreadcrumbs(model, request, response);
    }

    /**
     * Link Generator
     *
     * @param model
     * @param request
     * @param response
     */
    public void generateLink(
            ModelAndView model,
            HttpServletRequest request,
            HttpServletResponse response) {
        this.contextPath = request.getContextPath();
        this.contextPathAdmin = request.getContextPath() + "/admin";
        this.path = request.getServletPath();
        this.fullPath = this.contextPath + request.getServletPath();

        model.addObject("contextPath", this.contextPath);
        model.addObject("contextPathAdmin", this.contextPathAdmin);
        model.addObject("path", this.path);
        model.addObject("fullPath", this.fullPath);
    }

    /**
     * BreadCrumbs Generator
     *
     * @param model
     * @param request
     * @param response
     * @throws java.lang.Exception
     */
    public void generateBreadcrumbs(
            ModelAndView model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String[] pathSplit = this.path.split("/");
        String link = this.contextPath;
        String description;
        this.listBreadCrumbs.removeAll(listBreadCrumbs);

        for (String paths : pathSplit) {
            if (!paths.equals("")) {
                BreadCrumbs breadCrumbs = new BreadCrumbs();
                link = link + "/" + paths;
                RfTingkat rfTingkat = rfTingkatDao.getById(paths);
                if (rfTingkat != null) {
                    description = rfTingkat.getDescription();
                } else {
                    RfKelas rfKelas = rfKelasDao.getById(paths);
                    if (rfKelas != null) {
                        description = rfKelas.getDescription();
                    } else {
                        RfPelajaran rfPelajaran = rfPelajaranDao.getById(paths);
                        if (rfPelajaran != null) {
                            description = rfPelajaran.getDescription();
                        } else {
                            description = paths;
                        }
                    }
                }

                breadCrumbs.setLink(link);
                breadCrumbs.setDescription(description.toUpperCase());
                this.listBreadCrumbs.add(breadCrumbs);
            }
        }

        model.addObject("listBreadCrumbs", this.listBreadCrumbs);
    }
    
    /**
     * Set Session
     *
     * @param request
     * @param key
     * @param value
     */
    public void setSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * Get Session
     *
     * @param request
     * @param key
     * @return Object
     */
    public Object getSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        return session.getAttribute("key");
    }

    /**
     * Setting jika error
     *
     * @param model
     * @param e
     */
    public void setError(ModelAndView model, Exception e) {
        model.setViewName("error");
        model.addObject("title", "Error Page");
        model.addObject("exception", e.toString());
    }
}
