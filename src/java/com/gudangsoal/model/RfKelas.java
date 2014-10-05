package com.gudangsoal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="rf_kelas")
public class RfKelas implements Serializable {
    
    @Column(name="date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Id @Column(name="id") private String id;
    @Column(name="tingkat_id") private String tingkatId;
    @Column(name="description") private String description;
    @Column(name="urutan") private Integer urutan;
    
    @ManyToOne
    @JoinColumn(name="tingkat_id", insertable=false, updatable=false)
    private RfTingkat rfTingkat;
    
    public RfKelas(){
        this.date = new Date();
    }
    
    public RfKelas(Date date){
        this.date = date;
        this.id = "";
        this.tingkatId = "";
        this.description = "";
        this.urutan = 0;
    }

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

    public String getTingkatId() {
        return tingkatId;
    }

    public void setTingkatId(String tingkatId) {
        this.tingkatId = tingkatId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public RfTingkat getRfTingkat() {
        return rfTingkat;
    }

    public void setRfTingkat(RfTingkat rfTingkat) {
        this.rfTingkat = rfTingkat;
    }
    
    public void merge(RfKelas object){
        this.date = (object.getDate() == null) ? this.date : object.getDate();
        this.id = (object.getId() == null) ? this.id : object.getId();
        this.tingkatId = (object.getTingkatId() == null) ? this.tingkatId : object.getTingkatId();
        this.description = (object.getDescription() == null) ? this.description : object.getDescription();
        this.urutan = (object.getUrutan() == null) ? this.urutan : object.getUrutan();
    }
    
}
