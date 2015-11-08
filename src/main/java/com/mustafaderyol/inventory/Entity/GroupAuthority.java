/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mustafaderyol.inventory.Entity;

import java.io.Serializable;
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
@Entity(name="GROUPAUTHORITY")

@NamedQueries({
    @NamedQuery(name="allGroupAuthority", query="SELECT ag FROM GROUPAUTHORITY ag"),
    @NamedQuery(name="xGroupAuthority", query="SELECT ag FROM GROUPAUTHORITY ag WHERE ag.group=:group")
})
public class GroupAuthority implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="GROUPFK",nullable=false)
    private GroupEntity group;
    
    @OneToOne
    @JoinColumn(name="AUTHORITYFK",nullable=false)
    private Authority authority;
    
    @Column(name="GA_SELECT",nullable=false)
    private Boolean authoritySelect;
    
    @Column(name="GA_INSERT",nullable=false)
    private Boolean authorityInsert;
    
    @Column(name="GA_UPDATE",nullable=false)
    private Boolean authorityUpdate;
    
    @Column(name="GA_DELETE",nullable=false)
    private Boolean authorityDelete;

    public GroupAuthority() {
    }

    public GroupAuthority(GroupEntity group, Authority authority, Boolean authoritySelect, Boolean authorityInsert, Boolean authorityUpdate, Boolean authorityDelete) {
        this.group = group;
        this.authority = authority;
        this.authoritySelect = authoritySelect;
        this.authorityInsert = authorityInsert;
        this.authorityUpdate = authorityUpdate;
        this.authorityDelete = authorityDelete;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public Boolean getAuthoritySelect() {
        return authoritySelect;
    }

    public void setAuthoritySelect(Boolean authoritySelect) {
        this.authoritySelect = authoritySelect;
    }

    public Boolean getAuthorityInsert() {
        return authorityInsert;
    }

    public void setAuthorityInsert(Boolean authorityInsert) {
        this.authorityInsert = authorityInsert;
    }

    public Boolean getAuthorityUpdate() {
        return authorityUpdate;
    }

    public void setAuthorityUpdate(Boolean authorityUpdate) {
        this.authorityUpdate = authorityUpdate;
    }

    public Boolean getAuthorityDelete() {
        return authorityDelete;
    }

    public void setAuthorityDelete(Boolean authorityDelete) {
        this.authorityDelete = authorityDelete;
    }
}
