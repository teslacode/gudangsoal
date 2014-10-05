/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gudangsoal.dao;

import com.gudangsoal.service.HibernateUtil;
import org.hibernate.Session;

public class Dao {
    
    protected Session session;
    
    /**
     * Open Connection
     */
    public void Open() throws Exception {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
