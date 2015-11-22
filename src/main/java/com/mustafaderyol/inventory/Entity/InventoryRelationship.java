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
@Entity(name="INVENTORYRELATIONSHIP")
@NamedQueries({
    @NamedQuery(name="allInventoryRelationshipByInvetory1",query="SELECT c FROM INVENTORYRELATIONSHIP c WHERE c.inventory1 = :inventory1"),
    @NamedQuery(name="allInventoryRelationshipByInvetory2",query="SELECT c FROM INVENTORYRELATIONSHIP c WHERE c.inventory2 = :inventory2")
})
public class InventoryRelationship {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="INVENTORY1FK")
    private Inventory inventory1;
    
    @OneToOne
    @JoinColumn(name="INVENTORY2FK")
    private Inventory inventory2;

    public InventoryRelationship() {
    }

    public InventoryRelationship(Inventory inventory1, Inventory inventory2) {
        this.inventory1 = inventory1;
        this.inventory2 = inventory2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory1() {
        return inventory1;
    }

    public void setInventory1(Inventory inventory1) {
        this.inventory1 = inventory1;
    }

    public Inventory getInventory2() {
        return inventory2;
    }

    public void setInventory2(Inventory inventory2) {
        this.inventory2 = inventory2;
    }
    
}
