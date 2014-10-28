package com.gudangsoal.dao;

import com.gudangsoal.model.Pilihan;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO Pilihan
 * 
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class PilihanDao extends Dao {

    /**
     * Simpan Pilihan
     *
     * @param object
     * @return Boolean
     * @throws java.lang.Exception
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
     * @return List Pilihan
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
     * @param id
     * @return Pilihan
     * @throws Exception
     */
    public Pilihan getById(Long id) throws Exception {
        this.Open();
        this.session.beginTransaction();

        Pilihan result = null;
        Criteria criteria = this.session.createCriteria(Pilihan.class);
        criteria.add(Restrictions.eq("id", id));
        if (!criteria.list().isEmpty()) {
            result = (Pilihan) criteria.list().get(0);
        }

        this.session.getTransaction().commit();
        return result;
    }

    /**
     * Get Pilihan
     *
     * @param pertanyaanId
     * @return List Pilihan
     * @throws Exception
     */
    public List<Pilihan> getByPertanyaanId(Long pertanyaanId) throws Exception {
        this.Open();
        this.session.beginTransaction();

        List<Pilihan> result;
        Criteria criteria = this.session.createCriteria(Pilihan.class);
        criteria.add(Restrictions.eq("pertanyaanId", pertanyaanId));
        result = criteria.list();

        this.session.getTransaction().commit();
        return result;
    }

}
