package com.gudangsoal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="pilihan")
public class Pilihan implements Serializable {
    
    @Column(name="date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Id @GenericGenerator(name="pilihan_id_seq" , strategy="increment")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="pilihan_id_seq")
    @Column(name="id") private Long id;
    @Column(name="pertanyaan_id") private Long pertanyaanId;
    @Column(name="description") private String description;
    @Column(name="isvalid") private Boolean isValid;
    
    @ManyToOne
    @JoinColumn(name = "pertanyaan_id", insertable = false, updatable = false)
    private Pertanyaan pertanyaan;
    
    public Pilihan(){
        this.date = new Date();
    }
    
    public Pilihan(Date date){
        this.date = date;
        this.id = null;
        this.pertanyaanId = null;
        this.description = "";
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Pertanyaan getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(Pertanyaan pertanyaan) {
        this.pertanyaan = pertanyaan;
    }
    
    public void merge(Pilihan object){
        this.date = (object.getDate() == null) ? this.date : object.getDate();
        this.id = (object.getId() == null) ? this.id : object.getId();
        this.pertanyaanId = (object.getPertanyaanId() == null) ? this.pertanyaanId : object.getPertanyaanId();
        this.description = (object.getDescription() == null) ? this.description : object.getDescription();
        this.isValid = (object.getIsValid() == null) ? this.isValid : object.getIsValid();
    }
    
}
