package com.gudangsoal.dao;

import com.gudangsoal.service.HibernateUtil;
import org.hibernate.Session;

/**
 * Parent semua DAO
 * 
 * @author Ade Fruandta
 */
public class Dao {
    
    //protected Session session;
    
    /**
     * Open Connection
     * 
     * @return 
     * @throws java.lang.Exception
     */
    public Session Open() throws Exception {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
