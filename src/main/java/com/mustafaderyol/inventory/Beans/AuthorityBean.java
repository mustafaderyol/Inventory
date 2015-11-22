package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Authority;
import com.mustafaderyol.inventory.idao.IAuthorityDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MstfDryl
 */
@Component(value = "authorityBean")
@Scope(value = "request")
public class AuthorityBean {
    
    @Autowired
    IAuthorityDao iAuthorityDao;
    
    List<Authority> autList = new ArrayList<Authority>();
    
    @PostConstruct
    private void postConstruct() {
        autList = iAuthorityDao.allFunc();
    }

    public List<Authority> getAutList() {
        return autList;
    }

    public void setAutList(List<Authority> autList) {
        this.autList = autList;
    }
    
}
