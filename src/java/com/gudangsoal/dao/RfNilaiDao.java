package com.gudangsoal.dao;

import com.gudangsoal.model.RfNilai;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RfNilaiDao extends Dao {
    
    /**
     * Simpan RfNilai
     * 
     * @param RfNilai
     * @return 
     */
    public Boolean save(RfNilai object) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfNilai
     * 
     * @return 
     */
    public List<RfNilai> getAll() throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        List result;
        Criteria criteria = this.session.createCriteria(RfNilai.class);
        criteria.addOrder(Order.asc("urutan"));
        result = criteria.list();
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfNilai
     * 
     * @return
     * @throws Exception 
     */
    public RfNilai getById(String id) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        RfNilai result = null;
        Criteria criteria = this.session.createCriteria(RfNilai.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (RfNilai) criteria.list().get(0);
        }
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get RfNilai
     * 
     * @return
     * @throws Exception 
     */
    public RfNilai getByHasil(Boolean hasil) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        RfNilai result = null;
        Criteria criteria = this.session.createCriteria(RfNilai.class);
        criteria.add(hasil == null ? Restrictions.isNull("hasil") : Restrictions.eq("hasil", hasil));
        if(!criteria.list().isEmpty()){
            result = (RfNilai) criteria.list().get(0);
        }
        
        this.session.getTransaction().commit();
        return result;
    }
    
}
