/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mustafaderyol.inventory.Beans;

import com.mustafaderyol.inventory.Entity.Personal;
import com.mustafaderyol.inventory.IDao.IPersonalDao;
import java.util.ArrayList;
import java.util.Date;
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

@Component(value = "personalBean")
@Scope(value = "request")
public class PersonalBean {
    FacesContext context = FacesContext.getCurrentInstance();
         
    @Autowired
    IPersonalDao iPersonalDao;
    
    Personal personal;
    List<Personal> personalList = new ArrayList<Personal>();
    
    @PostConstruct
    private void postConstruct() {
        personal = new Personal();
        personalList = iPersonalDao.allFunc();
    }
    
    public void popupRefresh(){
        personal = new Personal();
    }
    
    public void onRowCancel(){
        personalList = iPersonalDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    
    
    public void savePersonal(){
        try
        {
            Date date = new Date();
            personal.setCreatedDate(date);
            personal.setPassword("1");
            iPersonalDao.saveFunc(personal);
            personalList = iPersonalDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            personal = new Personal();
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
    
    public void deletePersonal(Personal personal)
    {
        try
        {
            iPersonalDao.deleteFunc(personal.getId());
            personalList = iPersonalDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
    
    public void updatePersonal(Personal personal){
        try
        {
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
    
    
}
