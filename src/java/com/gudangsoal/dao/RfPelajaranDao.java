package com.gudangsoal.dao;

import com.gudangsoal.model.RfPelajaran;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO RF Pelajaran
 * 
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class RfPelajaranDao extends Dao {
    
    /**
     * Simpan RfPelajaran
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean save(RfPelajaran object) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Delete RfPelajaran
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean delete(RfPelajaran object) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        Boolean result;
        session.delete(object);
        result = true;
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfPelajaran
     * 
     * @return List RfPelajaran
     * @throws java.lang.Exception 
     */
    public List<RfPelajaran> getAll() throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        List result;
        Criteria criteria = session.createCriteria(RfPelajaran.class);
        criteria.addOrder(Order.asc("urutan"));
        result = criteria.list();
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfPelajaran
     * 
     * @param tingkatId
     * @param kelasId
     * @return List RfPelajaran
     * @throws Exception 
     */
    public List<RfPelajaran> getAll(String tingkatId, String kelasId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        List result;
        Criteria criteria = session.createCriteria(RfPelajaran.class);
        criteria.addOrder(Order.asc("urutan"));
        criteria.add(Restrictions.eq("tingkatId", tingkatId));
        criteria.add(Restrictions.eq("kelasId", kelasId));
        result = criteria.list();
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfPelajaran
     * 
     * @param id
     * @return RfPelajaran
     * @throws Exception 
     */
    public RfPelajaran getById(String id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        RfPelajaran result = null;
        Criteria criteria = session.createCriteria(RfPelajaran.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (RfPelajaran) criteria.list().get(0);
        }
        
        session.getTransaction().commit();
        return result;
    }
    
}
