package com.mustafaderyol.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author MstfDryl
 */
@Entity(name="PARAMETER")
@NamedQueries({
     @NamedQuery(name="allParameter",query="SELECT p FROM PARAMETER p"),
     @NamedQuery(name="allParameterByCategory",query="SELECT p FROM PARAMETER p WHERE p.category = :category AND p.parentparameter IS NULL"),
     @NamedQuery(name="allParameterByParentParameter",query="SELECT p FROM PARAMETER p WHERE p.parentparameter = :parentparameter"),
     @NamedQuery(name="allParameterByParentParameterByCategory",query="SELECT p FROM PARAMETER p WHERE p.parentparameter = :parentparameter AND p.category= :category")
})
public class Parameter {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="CATEGORYFK")
    private Category category;
    
    @OneToOne
    @JoinColumn(name="PARENTPARAMETER",nullable=true)
    private Parameter parentparameter;
    
    @Column(name="NAME",nullable=false,length=60)
    private String name;
    
    //Select = true, Text = false
    @Column(name="SELECTORTEXT")
    private Boolean selectortext;
    
    @Column(name="STATUS")
    private Boolean status;

    public Parameter() {
    }

    public Parameter(Category category, Parameter parentparameter, String name, Boolean selectortext, Boolean status) {
        this.category = category;
        this.parentparameter = parentparameter;
        this.name = name;
        this.selectortext = selectortext;
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

    public Parameter getParentparameter() {
        return parentparameter;
    }

    public void setParentparameter(Parameter parentparameter) {
        this.parentparameter = parentparameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelectortext() {
        return selectortext;
    }

    public void setSelectortext(Boolean selectortext) {
        this.selectortext = selectortext;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
