package com.mustafaderyol.inventory.Entity;

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

/**
 *
 * @author MstfDryl
 */
@Entity(name="GROUP")
@NamedQueries({
    @NamedQuery(name="allGroup",query="SELECT g FROM GROUP g")
})
public class Group {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="NAME",nullable=false,length=150)
    private String name;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="AUTHORITYFK")
    private List<Authority> authority;
    
    @Column(name="STATUS")
    private Boolean status;

    public Group() {
    }

    public Group(String name, List<Authority> authority, Boolean status) {
        this.name = name;
        this.authority = authority;
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

    public List<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
