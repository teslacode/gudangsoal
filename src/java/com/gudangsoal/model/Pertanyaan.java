package com.gudangsoal.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 * Model Pertanyaan
 * 
 * @author Ade Fruandta
 */
@Entity
@Table(name="pertanyaan")
public class Pertanyaan implements Serializable {
    
    @Column(name="date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Id @GenericGenerator(name="pertanyaan_id_seq" , strategy="increment")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="pertanyaan_id_seq")
    @Column(name="id") private Long id;
    @Column(name="tingkat_id") private String tingkatId;
    @Column(name="kelas_id") private String kelasId;
    @Column(name="pelajaran_id") private String pelajaranId;
    @Column(name="description") private String description;
    @Column(name="isactive") private Boolean isActive;
    
    @Transient private String status;
    @Transient private Boolean hasil;
    @Transient private Integer nilai;
    @Transient private Long pilihanId;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="pertanyaan")
    private List<Pilihan> listPilihan;
    
    /**
     * Default Constructor
     * date = current date
     */
    public Pertanyaan(){
        this.date = new Date();
    }
    
    /**
     * Constructor
     * id = null
     * tingkatId = empty string
     * kelasId = empty string
     * pelajaranId = empty string
     * description = empty string
     * isActive = false
     * 
     * @param date 
     */
    public Pertanyaan(Date date){
        this.date = date;
        this.id = null;
        this.tingkatId = "";
        this.kelasId = "";
        this.pelajaranId = "";
        this.description = "";
        this.isActive = false;
    }
    
    /**
     * Constructor
     * id = null
     * isActive = false
     * 
     * @param date
     * @param tingkatId
     * @param kelasId
     * @param pelajaranId 
     */
    public Pertanyaan(Date date, String tingkatId, String kelasId, String pelajaranId){
        this.date = date;
        this.id = null;
        this.tingkatId = tingkatId;
        this.kelasId = kelasId;
        this.pelajaranId = pelajaranId;
        this.description = "";
        this.isActive = false;
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

    public String getTingkatId() {
        return tingkatId;
    }

    public void setTingkatId(String tingkatId) {
        this.tingkatId = tingkatId;
    }

    public String getKelasId() {
        return kelasId;
    }

    public void setKelasId(String kelasId) {
        this.kelasId = kelasId;
    }

    public String getPelajaranId() {
        return pelajaranId;
    }

    public void setPelajaranId(String pelajaranId) {
        this.pelajaranId = pelajaranId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return (isActive? "Aktif":"Tidak Aktif");
    }

    public void setStatus(String status) {
        this.status = status;
        this.isActive = (status.equals("Aktif"));
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

    public Long getPilihanId() {
        return pilihanId;
    }

    public void setPilihanId(Long pilihanId) {
        this.pilihanId = pilihanId;
    }

    public List<Pilihan> getListPilihan() {
        return listPilihan;
    }

    public void setListPilihan(List<Pilihan> listPilihan) {
        this.listPilihan = listPilihan;
        Collections.shuffle(this.listPilihan);
    }
    
    /**
     * Menggabungkan HasilPertanyaan dari luar.
     * Mereplace semua attribute yang null.
     * 
     * @param object 
     */
    public void merge(Pertanyaan object){
        this.date = (object.getDate() == null) ? this.date : object.getDate();
        this.id = (object.getId() == null) ? this.id : object.getId();
        this.tingkatId = (object.getTingkatId() == null) ? this.tingkatId : object.getTingkatId();
        this.kelasId = (object.getKelasId() == null) ? this.kelasId : object.getKelasId();
        this.pelajaranId = (object.getPelajaranId() == null) ? this.pelajaranId : object.getPelajaranId();
        this.description = (object.getDescription() == null) ? this.description : object.getDescription();
        this.isActive = (object.getIsActive() == null) ? this.isActive : object.getIsActive();
    }
    
}
