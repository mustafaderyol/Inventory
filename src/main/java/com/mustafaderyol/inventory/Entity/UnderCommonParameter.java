package com.mustafaderyol.inventory.Entity;

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
@Entity(name="UNDERCOMMONPARAMETER")
@NamedQueries({
     @NamedQuery(name="allUnderCommonParameter",query="SELECT p FROM UNDERCOMMONPARAMETER p"),
     @NamedQuery(name="allUnderCommonParameterByCategory",query="SELECT p FROM UNDERCOMMONPARAMETER p WHERE p.category = :category"),
     @NamedQuery(name="allUnderCommonParameterByCommonParameter",query="SELECT p FROM UNDERCOMMONPARAMETER p WHERE p.commonparameter = :commonparameter"),
     @NamedQuery(name="allUnderCommonParameterByCommonParameterAndCategory",query="SELECT p FROM UNDERCOMMONPARAMETER p WHERE p.commonparameter = :commonparameter AND p.category = :category")
})
public class UnderCommonParameter {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="NAME",nullable=false,length=60)
    private String name;
    
    @OneToOne
    @JoinColumn(name="CATEGORYFK",nullable=false)
    private Category category;
    
    @OneToOne
    @JoinColumn(name="COMMONPARAMETERFK",nullable=false)
    private CommonParameter commonparameter;
    
    @Column(name="STATUS")
    private Boolean status;

    public UnderCommonParameter() {
    }

    public UnderCommonParameter(String name, Category category, CommonParameter commonparameter, Boolean status) {
        this.name = name;
        this.category = category;
        this.commonparameter = commonparameter;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CommonParameter getCommonParameter() {
        return commonparameter;
    }

    public void setCommonParameter(CommonParameter commonparameter) {
        this.commonparameter = commonparameter;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
