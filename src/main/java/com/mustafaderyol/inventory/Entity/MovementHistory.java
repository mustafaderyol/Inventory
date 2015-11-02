package com.mustafaderyol.inventory.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MstfDryl
 */
@Entity(name="MOVEMENTHISTORY")
@NamedQueries({
    @NamedQuery(name="allMovementHistory",query="SELECT c FROM MOVEMENTHISTORY c")
})
public class MovementHistory {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="INVENTORYFK")
    private Inventory inventory;
    
    @OneToOne
    @JoinColumn(name="PERSONALFK")
    private Personal personal;
    
    @OneToOne
    @JoinColumn(name="UNITFK")
    private Unit unit;
    
    @OneToOne
    @JoinColumn(name="LOCATIONFK")
    private Location location;
    
    @Column(name="LOCATIONDETAIL",nullable=true,length=1000)
    private String locationdetail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATEDDATE",nullable=false)
    private Date createdDate;
    
    //if status is true -> active movement history
    @Column(name="STATUS")
    private Boolean status;

    public MovementHistory() {
    }

    public MovementHistory(Inventory inventory, Personal personal, Unit unit, Location location, String locationdetail, Date createdDate, Boolean status) {
        this.inventory = inventory;
        this.personal = personal;
        this.unit = unit;
        this.location = location;
        this.locationdetail = locationdetail;
        this.createdDate = createdDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationdetail() {
        return locationdetail;
    }

    public void setLocationdetail(String locationdetail) {
        this.locationdetail = locationdetail;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
