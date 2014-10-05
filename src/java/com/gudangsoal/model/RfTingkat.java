package com.gudangsoal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="rf_tingkat")
public class RfTingkat implements Serializable {
    
    @Column(name="date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Id @Column(name="id") private String id;
    @Column(name="description") private String description;
    @Column(name="urutan") private Integer urutan;
    
    public RfTingkat(){
        this.date = new Date();
    }
    
    public RfTingkat(Date date){
        this.date = date;
        this.id = "";
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
    
    public void merge(RfTingkat object){
        this.date = (object.getDate() == null) ? this.date : object.getDate();
        this.id = (object.getId() == null) ? this.id : object.getId();
        this.description = (object.getDescription() == null) ? this.description : object.getDescription();
        this.urutan = (object.getUrutan() == null) ? this.urutan : object.getUrutan();
    }
    
}
