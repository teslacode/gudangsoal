package com.gudangsoal.dao;

import com.gudangsoal.model.Pilihan;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PilihanDao extends Dao {
    
    /**
     * Simpan Pilihan
     * 
     * @param Pilihan
     * @return 
     */
    public Boolean save(Pilihan object) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get Pilihan
     * 
     * @return
     * @throws Exception 
     */
    public List<Pilihan> getAll() throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        List result;
        Criteria criteria = this.session.createCriteria(Pilihan.class);
        result = criteria.list();
        
        this.session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get Pilihan
     * 
     * @return
     * @throws Exception 
     */
    public Pilihan getById(Long id) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Pilihan result = null;
        Criteria criteria = this.session.createCriteria(Pilihan.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (Pilihan) criteria.list().get(0);
        }
        
        this.session.getTransaction().commit();
        return result;
    }
    
}
