package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Personal;
import com.mustafaderyol.inventory.entity.PersonalRoles;
import com.mustafaderyol.inventory.idao.IPersonalDao;
import com.mustafaderyol.inventory.idao.IPersonalRolesDao;
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
@Component(value = "personalAuthBean")
@Scope(value = "request")
public class PersonalAuthBean{
    FacesContext context = FacesContext.getCurrentInstance();
         
    @Autowired
    IPersonalDao iPersonalDao;
    
    @Autowired
    IPersonalRolesDao iPersonalRolesDao;
    
    Personal personal;
    List<Personal> personalList = new ArrayList<Personal>();
    
    PersonalRoles personalRoles;
    List<PersonalRoles> personalRolesList = new ArrayList<PersonalRoles>();
    
    @PostConstruct
    private void postConstruct() {
        personal = new Personal();
        personalRoles = new PersonalRoles();
        personalList = iPersonalDao.allFunc();
    }
    
    public void popupRefresh(){
        this.personal = new Personal();
    }
    
    public List<PersonalRoles> getRoles(Long id)
    {
        return iPersonalRolesDao.findByPersonalIdFunc(id);
    }
    
    public void savePersonal(){
        try
        {
            iPersonalRolesDao.updateFunc(personalRoles);
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
    
    public void deletePersonal(Long id)
    {
        try
        {
            iPersonalRolesDao.deleteFunc(id);
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

    public PersonalRoles getPersonalRoles() {
        return personalRoles;
    }

    public void setPersonalRoles(PersonalRoles personalRoles) {
        this.personalRoles = personalRoles;
    }

    public List<PersonalRoles> getPersonalRolesList() {
        return personalRolesList;
    }

    public void setPersonalRolesList(List<PersonalRoles> personalRolesList) {
        this.personalRolesList = personalRolesList;
    }

}
