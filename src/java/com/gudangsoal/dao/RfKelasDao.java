package com.gudangsoal.dao;

import com.gudangsoal.model.RfKelas;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO RF Kelas
 * 
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class RfKelasDao extends Dao {
    
    /**
     * Simpan RfKelas
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean save(RfKelas object) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Delete RfKelas
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean delete(RfKelas object) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        Boolean result;
        session.delete(object);
        result = true;
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfKelas
     * 
     * @return List RfKelas
     * @throws java.lang.Exception 
     */
    public List<RfKelas> getAll() throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        List result;
        Criteria criteria = session.createCriteria(RfKelas.class);
        criteria.addOrder(Order.asc("urutan"));
        result = criteria.list();
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfKelas
     * 
     * @param tingkatId
     * @return List RfKelas
     * @throws java.lang.Exception 
     */
    public List<RfKelas> getAll(String tingkatId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        List result;
        Criteria criteria = session.createCriteria(RfKelas.class);
        criteria.addOrder(Order.asc("urutan"));
        criteria.add(Restrictions.eq("tingkatId", tingkatId));
        result = criteria.list();
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfKelas
     * 
     * @param id
     * @return RfKelas
     * @throws Exception 
     */
    public RfKelas getById(String id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        RfKelas result = null;
        Criteria criteria = session.createCriteria(RfKelas.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (RfKelas) criteria.list().get(0);
        }
        
        session.getTransaction().commit();
        return result;
    }
    
}
