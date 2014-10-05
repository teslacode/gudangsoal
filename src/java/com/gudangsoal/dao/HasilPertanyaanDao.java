package com.gudangsoal.dao;

import com.gudangsoal.model.HasilPertanyaan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HasilPertanyaanDao extends Dao {

    /**
     * Simpan HasilPertanyaan
     * 
     * @param HasilPertanyaan
     * @return 
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
