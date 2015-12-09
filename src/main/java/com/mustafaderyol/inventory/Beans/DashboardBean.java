package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.IInventoryDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MstfDryl
 */
@Component(value = "dashboardBean")
@Scope(value = "request")
public class DashboardBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    IInventoryDao iInventoryDao;
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    
    @PostConstruct
    private void postConstruct() {
    }
    
    public int countSavedInventoryByCatogory(Long id)
    {
        return iInventoryDao.allInventoryByCategoryId(iCategoryDao.findByIdFunc(id)).size();
    }
}
