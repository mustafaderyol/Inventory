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
@Entity(name="INVENTORYDETAILPARAMETER")
@NamedQueries({
    @NamedQuery(name="allInventoryDetailParameter",query="SELECT a FROM INVENTORYDETAILPARAMETER a")
})
public class InventoryDetailParameter {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="PARAMETERFK",nullable=false)
    private Parameter parameter;
    
    @OneToOne
    @JoinColumn(name="ANSWERPARAMETERFK",nullable=true)
    private Parameter answerparameter;
    
    @Column(name="TEXT", nullable=true)
    private String text;
    
    public InventoryDetailParameter() {
    }

    public InventoryDetailParameter(Parameter parameter, Parameter answerparameter, String text) {
        this.parameter = parameter;
        this.answerparameter = answerparameter;
        this.text = text;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Parameter getAnswerparameter() {
        return answerparameter;
    }

    public void setAnswerparameter(Parameter answerparameter) {
        this.answerparameter = answerparameter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
