package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Personal;
import com.mustafaderyol.inventory.idao.IPersonalDao;
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
@Component(value = "passwordChangeBean")
@Scope(value = "request")
public class PasswordChangeBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    IPersonalDao iPersonalDao;
    
    Personal personal,oldPers,newPers,newPersRepeat;
     
    @PostConstruct
    private void postConstruct() {
        personal = new Personal();
        oldPers = new Personal();
        newPers = new Personal();
        newPersRepeat = new Personal();
    }
    
    public String change(String email){
        
        try
        {
            Personal pers = iPersonalDao.login(email, this.oldPers.getPassword());
            
            if(pers == null)
            {
                oldPers = new Personal();
                newPers = new Personal();
                newPersRepeat = new Personal();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Eski Parola Hatalı"));
            }
            else
            {
                if(this.newPers.getPassword().equals(this.newPersRepeat.getPassword()))
                {
                    pers.setPassword(newPers.getPassword());
                    iPersonalDao.updateFunc(pers);
                    
                    oldPers = new Personal();
                    newPers = new Personal();
                    newPersRepeat = new Personal();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "İşlem Başarılı", "İşlem Başarılı."));
                
                    return "login.xhtml?faces-redirect=true";
                }
                else
                {
                    oldPers = new Personal();
                    newPers = new Personal();
                    newPersRepeat = new Personal();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Yeni Parola ile Yeni Parola Tekrar Uyuşmuyor."));
                }
            }
            
        }
        catch(Exception e)
        {
            oldPers = new Personal();
            newPers = new Personal();
            newPersRepeat = new Personal();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "HATA"+e)); 
        }
        return "";
        
    }
    
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    
    public Personal getOldPers() {
        return oldPers;
    }

    public void setOldPers(Personal oldPers) {
        this.oldPers = oldPers;
    }

    public Personal getNewPers() {
        return newPers;
    }

    public void setNewPers(Personal newPers) {
        this.newPers = newPers;
    }

    public Personal getNewPersRepeat() {
        return newPersRepeat;
    }

    public void setNewPersRepeat(Personal newPersRepeat) {
        this.newPersRepeat = newPersRepeat;
    }
    
}
