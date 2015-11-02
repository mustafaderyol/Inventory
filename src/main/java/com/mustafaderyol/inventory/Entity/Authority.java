package com.mustafaderyol.inventory.Entity;

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
@Entity(name="AUTHORITY")
@NamedQueries({
    @NamedQuery(name="allAuthority",query="SELECT a FROM AUTHORITY a")
})
public class Authority {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="NAME",nullable=false,length=150)
    private String name;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
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
   
}
