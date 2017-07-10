package xx.collection.bean;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Pd_sim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="pd"
    ,catalog="study3"
)

public class Pd_sim  implements java.io.Serializable {


    // Fields    

     private Integer th;
     private Integer zsdbh;
     private Integer zbh;
     private Integer CId;
     private String tg;
     private Integer da;
     private Integer csrcs;
     private Integer zqrcs;
     private Integer nyd;
     private String md5;


    // Constructors

    /** default constructor */
    public Pd_sim() {
    }

    
    /** full constructor */
    public Pd_sim(Integer zsdbh, Integer zbh, Integer CId, String tg, Integer da, Integer csrcs, Integer zqrcs, Integer nyd, String md5) {
        this.zsdbh = zsdbh;
        this.zbh = zbh;
        this.CId = CId;
        this.tg = tg;
        this.da = da;
        this.csrcs = csrcs;
        this.zqrcs = zqrcs;
        this.nyd = nyd;
        this.md5 = md5;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="th", unique=true, nullable=false)

    public Integer getTh() {
        return this.th;
    }
    
    public void setTh(Integer th) {
        this.th = th;
    }
    
    @Column(name="zsdbh")

    public Integer getZsdbh() {
        return this.zsdbh;
    }
    
    public void setZsdbh(Integer zsdbh) {
        this.zsdbh = zsdbh;
    }
    
    @Column(name="zbh")

    public Integer getZbh() {
        return this.zbh;
    }
    
    public void setZbh(Integer zbh) {
        this.zbh = zbh;
    }
    
    @Column(name="c_id")

    public Integer getCId() {
        return this.CId;
    }
    
    public void setCId(Integer CId) {
        this.CId = CId;
    }
    
    @Column(name="tg", length=300)

    public String getTg() {
        return this.tg;
    }
    
    public void setTg(String tg) {
        this.tg = tg;
    }
    
    @Column(name="da")

    public Integer getDa() {
        return this.da;
    }
    
    public void setDa(Integer da) {
        this.da = da;
    }
    
    @Column(name="csrcs")

    public Integer getCsrcs() {
        return this.csrcs;
    }
    
    public void setCsrcs(Integer csrcs) {
        this.csrcs = csrcs;
    }
    
    @Column(name="zqrcs")

    public Integer getZqrcs() {
        return this.zqrcs;
    }
    
    public void setZqrcs(Integer zqrcs) {
        this.zqrcs = zqrcs;
    }
    
    @Column(name="nyd")

    public Integer getNyd() {
        return this.nyd;
    }
    
    public void setNyd(Integer nyd) {
        this.nyd = nyd;
    }
    
    @Column(name="md5", length=200)

    public String getMd5() {
        return this.md5;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
   








}