package com.gudangsoal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Model Hasil Pertanyaan
 * 
 * @author Ade Fruandta
 */
@Entity
@Table(name="hasil_pertanyaan")
public class HasilPertanyaan implements Serializable {
    
    @Column(name="date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Id @GenericGenerator(name="hasil_pertanyaan_id_seq" , strategy="increment")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hasil_pertanyaan_id_seq")
    @Column(name="id") private Long id;
    @Column(name="pertanyaan_id") private Long pertanyaanId;
    @Column(name="hasil") private Boolean hasil;

    /**
     * Default Constructor
     * date = current date
     */
    public HasilPertanyaan(){
        this.date = new Date();
    }
    
    /**
     * Constructor
     * id = null
     * pertanyaanId = 0
     * hasil = null
     * 
     * @param date 
     */
    public HasilPertanyaan(Date date){
        this.date = date;
        this.id = null;
        this.pertanyaanId = 0L;
        this.hasil = null;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPertanyaanId() {
        return pertanyaanId;
    }

    public void setPertanyaanId(Long pertanyaanId) {
        this.pertanyaanId = pertanyaanId;
    }

    public Boolean getHasil() {
        return hasil;
    }

    public void setHasil(Boolean hasil) {
        this.hasil = hasil;
    }
    
    /**
     * Menggabungkan HasilPertanyaan dari luar.
     * Mereplace semua attribute yang null.
     * 
     * @param object 
     */
    public void merge(HasilPertanyaan object){
        this.date = (object.getDate() == null) ? this.date : object.getDate();
        this.id = (object.getId() == null) ? this.id : object.getId();
        this.pertanyaanId = (object.getPertanyaanId() == null) ? this.pertanyaanId : object.getPertanyaanId();
        this.hasil = (object.getHasil() == null) ? this.hasil : object.getHasil();
    }
    
}
