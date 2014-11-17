package com.gudangsoal.dao;

import com.gudangsoal.model.RfNilai;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO RF Nilai
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class RfNilaiDao extends Dao {
    
    /**
     * Simpan RfNilai
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean save(RfNilai object) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfNilai
     * 
     * @return List RfNilai
     * @throws java.lang.Exception
     */
    public List<RfNilai> getAll() throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        List result;
        Criteria criteria = session.createCriteria(RfNilai.class);
        criteria.addOrder(Order.asc("urutan"));
        result = criteria.list();
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfNilai
     * 
     * @param id
     * @return RfNilai
     * @throws Exception 
     */
    public RfNilai getById(String id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        RfNilai result = null;
        Criteria criteria = session.createCriteria(RfNilai.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (RfNilai) criteria.list().get(0);
        }
        
        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfNilai
     * 
     * @param hasil
     * @return RfNilai
     * @throws Exception 
     */
    public RfNilai getByHasil(Boolean hasil) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        RfNilai result = null;
        Criteria criteria = session.createCriteria(RfNilai.class);
        criteria.add(hasil == null ? Restrictions.isNull("hasil") : Restrictions.eq("hasil", hasil));
        if(!criteria.list().isEmpty()){
            result = (RfNilai) criteria.list().get(0);
        }
        
        session.getTransaction().commit();
        return result;
    }
    
}
