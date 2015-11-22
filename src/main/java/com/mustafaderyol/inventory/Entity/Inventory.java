package com.mustafaderyol.inventory.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author MstfDryl
 */
@Entity(name="INVENTORY")
@NamedQueries({
    @NamedQuery(name="allInventory",query="SELECT a FROM INVENTORY a"),
    @NamedQuery(name="allInventoryByCategoryId",query="SELECT a FROM INVENTORY a WHERE a.category = :category ")
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
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "INVENTORYDETAILPARAMETERJOINTABLE", joinColumns = { 
                    @JoinColumn(name = "INVENTORYFK", nullable = false, updatable = false) }, 
                    inverseJoinColumns = { @JoinColumn(name = "INVENTORYDETAILPARAMETERFK", 
                                    nullable = false, updatable = false) })
    private List<InventoryDetailParameter> inventoryDetailParameter;
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "INVENTORYDETAILCOMMONPARAMETERJOINTABLE", joinColumns = { 
                    @JoinColumn(name = "INVENTORYFK", nullable = false, updatable = false) }, 
                    inverseJoinColumns = { @JoinColumn(name = "INVENTORYDETAILCOMMONPARAMETERFK", 
                                    nullable = false, updatable = false) })
    private List<InventoryDetailCommonParameter> inventoryDetailCommonParameter;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "MOVEMENTHISTORYJOINTABLE", joinColumns = { 
                    @JoinColumn(name = "INVENTORYFK", nullable = false, updatable = false) }, 
                    inverseJoinColumns = { @JoinColumn(name = "MOVEMENTHISTORYFK", 
                                    nullable = false, updatable = false) })
    private List<MovementHistory> movementHistory;
    
    @Column(name="NOTE",nullable=true,length=1000)
    private String note;
    

    public Inventory() {
    }

    public Inventory(Category category, List<InventoryDetailParameter> inventoryDetailParameter, List<InventoryDetailCommonParameter> inventoryDetailCommonParameter, List<MovementHistory> movementHistory, String note) {
        this.category = category;
        this.inventoryDetailParameter = inventoryDetailParameter;
        this.inventoryDetailCommonParameter = inventoryDetailCommonParameter;
        this.movementHistory = movementHistory;
        this.note = note;
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

    public List<InventoryDetailParameter> getInventoryDetailParameter() {
        return inventoryDetailParameter;
    }

    public void setInventoryDetailParameter(List<InventoryDetailParameter> inventoryDetailParameter) {
        this.inventoryDetailParameter = inventoryDetailParameter;
    }

    public List<InventoryDetailCommonParameter> getInventoryDetailCommonParameter() {
        return inventoryDetailCommonParameter;
    }

    public void setInventoryDetailCommonParameter(List<InventoryDetailCommonParameter> inventoryDetailCommonParameter) {
        this.inventoryDetailCommonParameter = inventoryDetailCommonParameter;
    }

    public List<MovementHistory> getMovementHistory() {
        return movementHistory;
    }

    public void setMovementHistory(List<MovementHistory> movementHistory) {
        this.movementHistory = movementHistory;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
