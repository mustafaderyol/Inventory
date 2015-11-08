
package com.mustafaderyol.inventory.Entity;

import java.io.Serializable;
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
@Entity(name="PERSONAL")
@NamedQueries
({
    @NamedQuery(name="allPersonal", query="SELECT d FROM PERSONAL d")
})
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="FIRSTNAME", length=60,nullable=false)
    private String firstname;
    
    @Column(name="LASTNAME", length=40,nullable=false)
    private String lastname;
    
    @Column(name="EMAIL", nullable=false,unique = true)
    private String email;
    
    @Column(name="PASSWORD", nullable=false)
    private String password;
    
    @Column(name="SEX",nullable=false)
    private Boolean sex;
    
    @OneToOne
    @JoinColumn(name="UNITFK",nullable=true)
    private Unit unit;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="GROUPFK",nullable=true)
    private List<GroupEntity> groups;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATEDDATE",nullable=false)
    private Date createdDate;

    public Personal() {
    }

    public Personal(String firstname, String lastname, String email, String password, Boolean sex, Unit unit, List<GroupEntity> groups, Date createdDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.unit = unit;
        this.groups = groups;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
}