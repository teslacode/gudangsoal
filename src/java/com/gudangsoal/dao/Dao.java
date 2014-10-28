package com.gudangsoal.dao;

import com.gudangsoal.service.HibernateUtil;
import org.hibernate.Session;

/**
 * Parent semua DAO
 * 
 * @author Ade Fruandta
 */
public class Dao {
    
    protected Session session;
    
    /**
     * Open Connection
     * 
     * @throws java.lang.Exception
     */
    public void Open() throws Exception {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
