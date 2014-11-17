package com.gudangsoal.dao;

import com.gudangsoal.model.Pilihan;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
        Session session = this.Open();
        session.beginTransaction();

        Boolean result;
        session.merge(object);
        result = true;

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Pilihan
     *
     * @return List Pilihan
     * @throws Exception
     */
    public List<Pilihan> getAll() throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Pilihan.class);
        result = criteria.list();

        session.getTransaction().commit();
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
        Session session = this.Open();
        session.beginTransaction();

        Pilihan result = null;
        Criteria criteria = session.createCriteria(Pilihan.class);
        criteria.add(Restrictions.eq("id", id));
        if (!criteria.list().isEmpty()) {
            result = (Pilihan) criteria.list().get(0);
        }

        session.getTransaction().commit();
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
        Session session = this.Open();
        session.beginTransaction();

        List<Pilihan> result;
        Criteria criteria = session.createCriteria(Pilihan.class);
        criteria.add(Restrictions.eq("pertanyaanId", pertanyaanId));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }

}
