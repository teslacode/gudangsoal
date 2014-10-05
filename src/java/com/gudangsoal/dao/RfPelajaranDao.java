package com.gudangsoal.dao;

import com.gudangsoal.model.RfPelajaran;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RfPelajaranDao extends Dao {
    
    /**
     * Simpan RfPelajaran
     * 
     * @param RfPelajaran
     * @return 
     */
    public Boolean save(RfPelajaran object) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Delete RfPelajaran
     * 
     * @param RfPelajaran
     * @return 
     */
    public Boolean delete(RfPelajaran object) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Boolean result;
        session.delete(object);
        result = true;
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfPelajaran
     * 
     * @return 
     */
    public List<RfPelajaran> getAll() throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        List result;
        Criteria criteria = this.session.createCriteria(RfPelajaran.class);
        criteria.addOrder(Order.asc("urutan"));
        result = criteria.list();
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfPelajaran
     * 
     * @param tingkatId
     * @param kelasId
     * @return
     * @throws Exception 
     */
    public List<RfPelajaran> getAll(String tingkatId, String kelasId) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        List result;
        Criteria criteria = this.session.createCriteria(RfPelajaran.class);
        criteria.addOrder(Order.asc("urutan"));
        criteria.add(Restrictions.eq("tingkatId", tingkatId));
        criteria.add(Restrictions.eq("kelasId", kelasId));
        result = criteria.list();
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfPelajaran
     * 
     * @return
     * @throws Exception 
     */
    public RfPelajaran getById(String id) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        RfPelajaran result = null;
        Criteria criteria = this.session.createCriteria(RfPelajaran.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (RfPelajaran) criteria.list().get(0);
        }
        
        this.session.getTransaction().commit();
        return result;
    }
    
}
