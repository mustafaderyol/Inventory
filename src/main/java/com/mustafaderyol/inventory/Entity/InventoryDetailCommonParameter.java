package com.mustafaderyol.inventory.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author MstfDryl
 */
@Entity(name="INVENTORYDETAILCOMMONPARAMETER")
@NamedQueries({
    @NamedQuery(name="allInventoryDetailCommonParameter",query="SELECT a FROM INVENTORYDETAILCOMMONPARAMETER a")
})
public class InventoryDetailCommonParameter {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="COMMONPARAMETERFK",nullable=false)
    private CommonParameter commonparameter;
    
    @OneToOne
    @JoinColumn(name="UNDERCOMMONPARAMETERFK",nullable=true)
    private UnderCommonParameter undercommonparameter;
    
    @Column(name="TEXT",nullable=true)
    private String text;

    public InventoryDetailCommonParameter() {
    }

    public InventoryDetailCommonParameter(CommonParameter commonparameter, UnderCommonParameter undercommonparameter, String text) {
        this.commonparameter = commonparameter;
        this.undercommonparameter = undercommonparameter;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommonParameter getCommonparameter() {
        return commonparameter;
    }

    public void setCommonparameter(CommonParameter commonparameter) {
        this.commonparameter = commonparameter;
    }

    public UnderCommonParameter getUndercommonparameter() {
        return undercommonparameter;
    }

    public void setUndercommonparameter(UnderCommonParameter undercommonparameter) {
        this.undercommonparameter = undercommonparameter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
