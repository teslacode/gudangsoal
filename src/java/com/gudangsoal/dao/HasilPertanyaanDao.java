package com.gudangsoal.dao;

import com.gudangsoal.model.HasilPertanyaan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO HasilPertanyaan
 * 
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class HasilPertanyaanDao extends Dao {

    /**
     * Simpan HasilPertanyaan
     * 
     * @param object
     * @return Boolean
     * @throws java.lang.Exception 
     */
    public Boolean save(HasilPertanyaan object) throws Exception {
        this.Open();
        this.session.beginTransaction();
        
        Boolean result;
        session.merge(object);
        result = true;
        
        this.session.getTransaction().commit();
        return result;
    }
    
}
