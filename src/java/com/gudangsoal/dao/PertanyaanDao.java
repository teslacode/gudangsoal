package com.gudangsoal.dao;

import com.gudangsoal.model.Pertanyaan;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO Pertanyaan
 *
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class PertanyaanDao extends Dao {

    /**
     * Simpan Pertanyaan
     *
     * @param object
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean save(Pertanyaan object) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Boolean result;
        session.merge(object);
        result = true;

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Pertanyaan
     *
     * @return List Pertanyaan
     * @throws Exception
     */
    public List<Pertanyaan> getAll() throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Pertanyaan.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Pertanyaan
     *
     * @param tingkatId
     * @param kelasId
     * @param pelajaranId
     * @return List Pertanyaan
     * @throws Exception
     */
    public List<Pertanyaan> getAll(String tingkatId, String kelasId, String pelajaranId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Pertanyaan.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("tingkatId", tingkatId));
        criteria.add(Restrictions.eq("kelasId", kelasId));
        criteria.add(Restrictions.eq("pelajaranId", pelajaranId));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Pertanyaan
     *
     * @param tingkatId
     * @param kelasId
     * @param pelajaranId
     * @param isActive
     * @return List Pertanyaan
     * @throws Exception
     */
    public List<Pertanyaan> getAll(String tingkatId, String kelasId, String pelajaranId, Boolean isActive) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Pertanyaan.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("tingkatId", tingkatId));
        criteria.add(Restrictions.eq("kelasId", kelasId));
        criteria.add(Restrictions.eq("pelajaranId", pelajaranId));
        criteria.add(Restrictions.eq("isActive", isActive));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }

    /**
     * Generate Pertanyaan dengan populasi data pertanyaan yang aktif
     *
     * @param tingkatId
     * @param kelasId
     * @param pelajaranId
     * @return List Pertanyaan
     * @throws java.lang.Exception
     */
    public List<Pertanyaan> generate(String tingkatId, String kelasId, String pelajaranId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List<Pertanyaan> result;
        Criteria criteria = session.createCriteria(Pertanyaan.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("tingkatId", tingkatId));
        criteria.add(Restrictions.eq("kelasId", kelasId));
        criteria.add(Restrictions.eq("pelajaranId", pelajaranId));
        criteria.add(Restrictions.eq("isActive", true));
        criteria.add(Restrictions.sqlRestriction("1=1 order by RANDOM()"));
        criteria.setMaxResults(10);
        result = criteria.list();
        Collections.shuffle(result);
        int i = 0;
        for (Pertanyaan pertanyaan : result) {
            pertanyaan.setNomorUrut(i++);
            Hibernate.initialize(pertanyaan.getListPilihan());
        }

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Pertanyaan
     *
     * @param id
     * @return Pertanyaan
     * @throws Exception
     */
    public Pertanyaan getById(Long id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Pertanyaan result = null;
        Criteria criteria = session.createCriteria(Pertanyaan.class);
        criteria.add(Restrictions.eq("id", id));
        if (!criteria.list().isEmpty()) {
            result = (Pertanyaan) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }

}
