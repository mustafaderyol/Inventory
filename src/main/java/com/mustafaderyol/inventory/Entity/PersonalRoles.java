package com.mustafaderyol.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author MstfDryl
 */
@Entity(name="PERSONALROLES")
@NamedQueries({
    @NamedQuery(name="allPersonalRoles",query="SELECT a FROM PERSONALROLES a"),
    @NamedQuery(name="allPersonalRolesByPersonalId",query="SELECT a FROM PERSONALROLES a WHERE a.personalid=:id")
})
public class PersonalRoles {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="PERSONALID",nullable=false,length=150)
    private Long personalid;
    
    @Column(name="NAME",nullable=false,length=150)
    private String name;

    public PersonalRoles() {
    }

    public PersonalRoles(Long personalid, String name) {
        this.personalid = personalid;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonalid() {
        return personalid;
    }

    public void setPersonalid(Long personalid) {
        this.personalid = personalid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
}
