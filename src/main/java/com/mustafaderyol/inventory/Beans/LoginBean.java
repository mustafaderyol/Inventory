package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Personal;
import com.mustafaderyol.inventory.idao.IPersonalDao;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MstfDryl
 */
@Component(value = "loginBean")
@Scope(value = "session")
public class LoginBean {
    
    @Autowired
    IPersonalDao iPersonalDao;
    
    Personal personal;
     
    @PostConstruct
    private void postConstruct() {
        personal = new Personal();
    }
    
    public void login(){
        try{
           personal = iPersonalDao.login(personal.getEmail(), personal.getPassword());
        }
        catch(Exception e){}
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    
}
