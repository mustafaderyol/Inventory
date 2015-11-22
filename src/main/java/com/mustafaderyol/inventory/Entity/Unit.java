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
@Entity(name="UNIT")
@NamedQueries({
    @NamedQuery(name="allUnit", query="SELECT u FROM UNIT u")
})
public class Unit {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="NAME",length=150,nullable=false)
    private String name;
    
    @OneToOne
    @JoinColumn(name="PARENTUNIT",nullable=true)
    private Unit parentunit;
    
    @Column(name="STATUS")
    private Boolean status;

    public Unit() {
    }

    public Unit(String name, Unit parentunit, Boolean status) {
        this.name = name;
        this.parentunit = parentunit;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getParentunit() {
        return parentunit;
    }

    public void setParentunit(Unit parentunit) {
        this.parentunit = parentunit;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
