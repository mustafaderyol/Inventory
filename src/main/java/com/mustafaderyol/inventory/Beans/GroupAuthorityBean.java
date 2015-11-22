package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Authority;
import com.mustafaderyol.inventory.entity.GroupAuthority;
import com.mustafaderyol.inventory.entity.GroupEntity;
import com.mustafaderyol.inventory.idao.IAuthorityDao;
import com.mustafaderyol.inventory.idao.IGroupAuthorityDao;
import com.mustafaderyol.inventory.idao.IGroupDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MstfDryl
 */
@Component(value = "groupAuthorityBean")
@ViewScoped
public class GroupAuthorityBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    IGroupAuthorityDao iGroupAuthorityDao;
    
    @Autowired
    IAuthorityDao iAuthorityDao;
    
    @Autowired
    IGroupDao iGroupDao;
    
    List<Authority> authorityList = new ArrayList<Authority>();
    Long authorityId;
    GroupEntity group;
    GroupAuthority groupAuthority;
    List<GroupAuthority> groupAuthorityList = new ArrayList<GroupAuthority>();
    
    
    @PostConstruct
    private void postConstruct() {
        group = new GroupEntity();
        groupAuthority = new GroupAuthority();
        authorityList = iAuthorityDao.allFunc();
    }
    
    public void popupRefresh(Long id){
        group = new GroupEntity();
        groupAuthority = new GroupAuthority();
        this.group = iGroupDao.findByIdFunc(id);
        groupAuthority.setGroup(group);
           
        groupAuthorityList = iGroupAuthorityDao.allByGroup(group);
        authorityList = iAuthorityDao.allFunc();
    }
    
    public void saveGroupAuthority(GroupEntity group){
        try
        {
            groupAuthority.setGroup(group);
            groupAuthority.setAuthority(iAuthorityDao.findByIdFunc(authorityId));
            iGroupAuthorityDao.saveFunc(groupAuthority);
            groupAuthorityList = iGroupAuthorityDao.allByGroup(group);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            groupAuthority = new GroupAuthority();
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public void deleteGroupAuthority(GroupAuthority group)
    {
        try
        {
            iGroupAuthorityDao.deleteFunc(group.getId());
            groupAuthorityList = iGroupAuthorityDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }

    public GroupAuthority getGroupAuthority() {
        return groupAuthority;
    }

    public void setGroupAuthority(GroupAuthority groupAuthority) {
        this.groupAuthority = groupAuthority;
    }

    public List<GroupAuthority> getGroupAuthorityList() {
        return groupAuthorityList;
    }

    public void setGroupAuthorityList(List<GroupAuthority> groupAuthorityList) {
        this.groupAuthorityList = groupAuthorityList;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
    
}
