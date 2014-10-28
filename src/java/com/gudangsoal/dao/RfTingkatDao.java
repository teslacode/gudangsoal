package com.gudangsoal.dao;

import com.gudangsoal.model.RfTingkat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO RF Tingkat
 * 
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class RfTingkatDao extends Dao {
    
    /**
     * Simpan RfTingkat
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean save(RfTingkat object) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Delete RfTingkat
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean delete(RfTingkat object) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Boolean result;
        session.delete(object);
        result = true;
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfTingkat
     * 
     * @return List RfTingkat
     * @throws java.lang.Exception 
     */
    public List<RfTingkat> getAll() throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        List result;
        Criteria criteria = this.session.createCriteria(RfTingkat.class);
        criteria.addOrder(Order.asc("urutan"));
        result = criteria.list();
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfTingkat
     * 
     * @param id
     * @return RfTingkat
     * @throws Exception 
     */
    public RfTingkat getById(String id) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        RfTingkat result = null;
        Criteria criteria = this.session.createCriteria(RfTingkat.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (RfTingkat) criteria.list().get(0);
        }
        
        this.session.getTransaction().commit();
        return result;
    }
    
}
