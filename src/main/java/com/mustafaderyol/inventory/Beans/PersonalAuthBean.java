package com.mustafaderyol.inventory.Beans;

import com.mustafaderyol.inventory.Entity.GroupEntity;
import com.mustafaderyol.inventory.Entity.Personal;
import com.mustafaderyol.inventory.IDao.IGroupDao;
import com.mustafaderyol.inventory.IDao.IPersonalDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author MstfDryl
 */
@Component(value = "personalAuthBean")
@Scope(value = "request")
public class PersonalAuthBean{
    FacesContext context = FacesContext.getCurrentInstance();
         
    @Autowired
    IPersonalDao iPersonalDao;
    
    @Autowired
    IGroupDao iGroupDao;
    
    Long groupId,personalId;
    Personal personal;
    List<Personal> personalList = new ArrayList<Personal>();
    
    List<GroupEntity> groupList = new ArrayList<GroupEntity>();
    
    
    @PostConstruct
    private void postConstruct() {
        personal = new Personal();
        personalList = iPersonalDao.allFunc();
        groupList = iGroupDao.allFunc();
    }
    
    public void popupRefresh(){
        this.personal = new Personal();
        groupList = iGroupDao.allFunc();
    }
    
    public void updatePersonal(){
        try
        {
            this.personal = iPersonalDao.findByIdFunc(personalId);
            personal.getGroups().add(iGroupDao.findByIdFunc(groupId));
            iPersonalDao.updateFunc(personal);
            personalList = iPersonalDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch( IllegalStateException ex ) 
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "Zaten Kayıtlı") );
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public void deletePersonalGroup(Personal personal,GroupEntity group)
    {
        try
        {
            personal.getGroups().remove(group);
            iPersonalDao.updateFunc(personal);
            personalList = iPersonalDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    public List<GroupEntity> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupEntity> groupList) {
        this.groupList = groupList;
    }    

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }
}
