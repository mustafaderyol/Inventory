package com.mustafaderyol.inventory.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MstfDryl
 */
@Entity(name="INVENTORY")
@NamedQueries({
    @NamedQuery(name="allInventory",query="SELECT a FROM INVENTORY a")
})
public class Inventory {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="CATEGORYFK")
    private Category category;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="PARAMETERFK")
    private List<Parameter> parameters;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="UNDERPARAMETERFK",nullable=true)
    private List<Parameter> underparameters;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="COMMONPARAMETERFK")
    private List<CommonParameter> commonparameters;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="UNDERCOMMONPARAMETERFK",nullable=true)
    private List<UnderCommonParameter> undercommonparameters;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATEDDATE",nullable=false)
    private Date createdDate;
    
    @Column(name="NOTE",nullable=true,length=1000)
    private String note;
    
    @Column(name="STATUS")
    private Boolean status;

    public Inventory() {
    }

    public Inventory(Category category, List<Parameter> parameters, List<Parameter> underparameters, List<CommonParameter> commonparameters, List<UnderCommonParameter> undercommonparameters, Date createdDate, String note, Boolean status) {
        this.category = category;
        this.parameters = parameters;
        this.underparameters = underparameters;
        this.commonparameters = commonparameters;
        this.undercommonparameters = undercommonparameters;
        this.createdDate = createdDate;
        this.note = note;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getUnderparameters() {
        return underparameters;
    }

    public void setUnderparameters(List<Parameter> underparameters) {
        this.underparameters = underparameters;
    }

    public List<CommonParameter> getCommonparameters() {
        return commonparameters;
    }

    public void setCommonparameters(List<CommonParameter> commonparameters) {
        this.commonparameters = commonparameters;
    }

    public List<UnderCommonParameter> getUndercommonparameters() {
        return undercommonparameters;
    }

    public void setUndercommonparameters(List<UnderCommonParameter> undercommonparameters) {
        this.undercommonparameters = undercommonparameters;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
