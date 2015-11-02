
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
@Entity(name="CATEGORY")
@NamedQueries({
    @NamedQuery(name="allCategory",query="SELECT c FROM CATEGORY c")
})
public class Category {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="NAME", nullable=false, length=80)
    private String name;
    
    @Column(name="STATUS")
    private Boolean status;

    public Category() {
    }

    public Category(String name, Boolean status) {
        this.name = name;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
