package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.idao.ICategoryDao;
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
@Component(value="headerMenuBean")
@Scope(value="request")
public class HeaderMenuBean {
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    List<Category> categoryList = new ArrayList<Category>();
    
    @PostConstruct
    private void postConstruct() {
        categoryList = iCategoryDao.allFunc();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
}
