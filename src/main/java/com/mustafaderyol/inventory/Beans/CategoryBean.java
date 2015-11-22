package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.idao.ICategoryDao;
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
@Component(value = "categoryBean")
@Scope(value = "request")
public class CategoryBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    Category category;
    List<Category> categoryList = new ArrayList<Category>();
    
    @PostConstruct
    private void postConstruct() {
        category = new Category();
        categoryList = iCategoryDao.allFunc();
    }
    
    public void popupRefresh(){
        category = new Category();
    }
    
    public void onRowCancel(){
        categoryList = iCategoryDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    
    
    public void saveCategory(){
        try
        {
            category.setStatus(true);
            iCategoryDao.saveFunc(category);
            categoryList = iCategoryDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            category = new Category();
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
    
    public void deleteCategory(Category category)
    {
        try
        {
            iCategoryDao.deleteFunc(category.getId());
            categoryList = iCategoryDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public void updateCategory(Category category){
        try
        {
            iCategoryDao.updateFunc(category);
            categoryList = iCategoryDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
