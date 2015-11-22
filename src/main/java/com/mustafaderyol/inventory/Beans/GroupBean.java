package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.GroupEntity;
import com.mustafaderyol.inventory.idao.IGroupDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MstfDryl
 */
@Component(value = "groupBean")
@Scope(value = "request")
public class GroupBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    IGroupDao iGroupDao;
    
    GroupEntity group;
    List<GroupEntity> groupList = new ArrayList<GroupEntity>();
    
    @PostConstruct
    private void postConstruct() {
        group = new GroupEntity();
        groupList = iGroupDao.allFunc();
    }
    
    public void popupRefresh(){
        group = new GroupEntity();
    }
    
    public void onRowCancel(){
        groupList = iGroupDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    
    
    public void saveGroup(){
        try
        {
            group.setStatus(true);
            iGroupDao.saveFunc(group);
            groupList = iGroupDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            group = new GroupEntity();
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
    
    public void deleteGroup(GroupEntity group)
    {
        try
        {
            iGroupDao.deleteFunc(group.getId());
            groupList = iGroupDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public void updateGroup(GroupEntity group){
        try
        {
            iGroupDao.updateFunc(group);
            groupList = iGroupDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public List<GroupEntity> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupEntity> groupList) {
        this.groupList = groupList;
    }
    
}
