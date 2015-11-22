package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.CommonParameter;
import com.mustafaderyol.inventory.entity.UnderCommonParameter;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.ICommonParameterDao;
import com.mustafaderyol.inventory.idao.IUnderCommonParameterDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MstfDryl
 */
@Component(value = "underCommonParameterBean")
@ViewScoped
public class UnderCommonParameterBean {
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    IUnderCommonParameterDao iUnderCommonParameterDao;
    
    @Autowired 
    ICategoryDao iCategoryDao;
    
    @Autowired
    ICommonParameterDao iCommonParameterDao;
    
    UnderCommonParameter underCommonParameter;
    List<UnderCommonParameter> underCommonParameterList = new ArrayList<UnderCommonParameter>();
    
    Long categoryId;
    List<Category> categoryList = new ArrayList<Category>();
    
    Long commonParameterId;
    List<CommonParameter> commonParameterList = new ArrayList<CommonParameter>();
    
    @PostConstruct
    private void postConstruct() {
        underCommonParameter = new UnderCommonParameter();
        underCommonParameterList  = iUnderCommonParameterDao.allFunc();
        categoryList = iCategoryDao.allFunc();
        commonParameterList = iCommonParameterDao.allFunc();
    }
    
    public void popupRefresh(){
        underCommonParameter = new UnderCommonParameter();
        underCommonParameterList  = iUnderCommonParameterDao.allFunc();
        categoryList = iCategoryDao.allFunc();
        commonParameterList = iCommonParameterDao.allFunc();
    }
    
    public void onRowCancel(){
        categoryList = iCategoryDao.allFunc();
        commonParameterList = iCommonParameterDao.allFunc();
        underCommonParameterList  = iUnderCommonParameterDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    
    
    public void saveUnderCommonParameter(){
        try
        {
            underCommonParameter.setStatus(true);
            underCommonParameter.setCategory(iCategoryDao.findByIdFunc(categoryId));
            underCommonParameter.setCommonParameter(iCommonParameterDao.findByIdFunc(commonParameterId));
            iUnderCommonParameterDao.saveFunc(underCommonParameter);
            underCommonParameterList  = iUnderCommonParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            underCommonParameter = new UnderCommonParameter();
          
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
     
    public void updateUnderCommonParameter(UnderCommonParameter underCommonParameter)
    {
        try
        {
            underCommonParameter.setCategory(iCategoryDao.findByIdFunc(categoryId));
            underCommonParameter.setCommonParameter(iCommonParameterDao.findByIdFunc(commonParameterId));
            iUnderCommonParameterDao.updateFunc(underCommonParameter);
            underCommonParameterList  = iUnderCommonParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
        
    }
    
    
    public void deleteUnderCommonParameter(UnderCommonParameter underCommonParameter)
    {
        try
        {
            iUnderCommonParameterDao.deleteFunc(underCommonParameter.getId());
            underCommonParameterList  = iUnderCommonParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }    

    public UnderCommonParameter getUnderCommonParameter() {
        return underCommonParameter;
    }

    public void setUnderCommonParameter(UnderCommonParameter underCommonParameter) {
        this.underCommonParameter = underCommonParameter;
    }

    public List<UnderCommonParameter> getUnderCommonParameterList() {
        return underCommonParameterList;
    }

    public void setUnderCommonParameterList(List<UnderCommonParameter> underCommonParameterList) {
        this.underCommonParameterList = underCommonParameterList;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Long getCommonParameterId() {
        return commonParameterId;
    }

    public void setCommonParameterId(Long commonParameterId) {
        this.commonParameterId = commonParameterId;
    }

    public List<CommonParameter> getCommonParameterList() {
        return commonParameterList;
    }

    public void setCommonParameterList(List<CommonParameter> commonParameterList) {
        this.commonParameterList = commonParameterList;
    }
}
