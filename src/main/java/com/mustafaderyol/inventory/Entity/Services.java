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
@Entity(name="SERVICES")
@NamedQueries({
    @NamedQuery(name="allServices",query="SELECT g FROM SERVICES g")
})
public class Services {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="CATEGORYFK")
    private Category category;
    
    @OneToOne
    @JoinColumn(name="MOVEMENTHISTORYFK")
    private MovementHistory movementhistory;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="GODATE",nullable=false)
    private Date goDate;
    
    @Column(name="GONOTE",nullable=false,length=1000)
    private String goNote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="COMEDATE",nullable=true)
    private Date comeDate;
    
    @Column(name="COMENOTE",nullable=true,length=1000)
    private String comeNote;
    
    @Column(name="STATUS")
    private Boolean status;

    public Services() {
    }

    public Services(Category category, MovementHistory movementhistory, Date goDate, String goNote, Date comeDate, String comeNote, Boolean status) {
        this.category = category;
        this.movementhistory = movementhistory;
        this.goDate = goDate;
        this.goNote = goNote;
        this.comeDate = comeDate;
        this.comeNote = comeNote;
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

    public MovementHistory getMovementhistory() {
        return movementhistory;
    }

    public void setMovementhistory(MovementHistory movementhistory) {
        this.movementhistory = movementhistory;
    }

    public Date getGoDate() {
        return goDate;
    }

    public void setGoDate(Date goDate) {
        this.goDate = goDate;
    }

    public String getGoNote() {
        return goNote;
    }

    public void setGoNote(String goNote) {
        this.goNote = goNote;
    }

    public Date getComeDate() {
        return comeDate;
    }

    public void setComeDate(Date comeDate) {
        this.comeDate = comeDate;
    }

    public String getComeNote() {
        return comeNote;
    }

    public void setComeNote(String comeNote) {
        this.comeNote = comeNote;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
