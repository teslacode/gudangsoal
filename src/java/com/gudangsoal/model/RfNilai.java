package com.gudangsoal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model RF Nilai
 * 
 * @author Ade Fruandta
 */
@Entity
@Table(name="rf_nilai")
public class RfNilai implements Serializable {
    
    @Column(name="date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Id @Column(name="id") private String id;
    @Column(name="description") private String description;
    @Column(name="hasil") private Boolean hasil;
    @Column(name="nilai") private Integer nilai;
    @Column(name="urutan") private Integer urutan;

    /**
     * Default Constructor
     * date = current date
     */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasil() {
        return hasil;
    }

    public void setHasil(Boolean hasil) {
        this.hasil = hasil;
    }

    public Integer getNilai() {
        return nilai;
    }

    public void setNilai(Integer nilai) {
        this.nilai = nilai;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }
    
    /**
     * Menggabungkan HasilPertanyaan dari luar.
     * Mereplace semua attribute yang null.
     * 
     * @param object 
     */
    public void merge(RfNilai object){
        this.date = (object.getDate() == null) ? this.date : object.getDate();
        this.id = (object.getId() == null) ? this.id : object.getId();
        this.description = (object.getDescription() == null) ? this.description : object.getDescription();
        this.hasil = (object.getHasil() == null) ? this.hasil : object.getHasil();
        this.nilai = (object.getNilai() == null) ? this.nilai : object.getNilai();
        this.urutan = (object.getUrutan() == null) ? this.urutan : object.getUrutan();
    }
    
}
