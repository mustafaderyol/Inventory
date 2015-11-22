package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.Parameter;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.IParameterDao;
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
@Component(value = "parameterBean")
@ViewScoped
public class ParameterBean {
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    IParameterDao iParameterDao;
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    Parameter parameter;
    Long parameterId,parameterId2;
    Long categoryId,categoryId2;
    List<Parameter> parameterList = new ArrayList<Parameter>();
    List<Category> categoryList = new ArrayList<Category>();
    
    
    @PostConstruct
    private void postConstruct() {
        parameter = new Parameter();
        parameterList  = iParameterDao.allFunc();
        categoryList = iCategoryDao.allFunc();
    }
    
    public void popupRefresh(){
        parameter = new Parameter();
        parameterList  = iParameterDao.allFunc();
        categoryList = iCategoryDao.allFunc();
    }
    
    public void onRowCancel(){
        parameterList  = iParameterDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    
    
    public void saveParameter(){
        try
        {
            parameter.setStatus(true);
            parameter.setCategory(iCategoryDao.findByIdFunc(categoryId));
            parameter.setParentparameter(iParameterDao.findByIdFunc(parameterId));
            iParameterDao.saveFunc(parameter);
            parameterList = iParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            parameter = new Parameter();
          
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
     
    public void updateParameter(Parameter parameter)
    {
        try
        {
            parameter.setCategory(iCategoryDao.findByIdFunc(categoryId2));
            parameter.setParentparameter(iParameterDao.findByIdFunc(parameterId2));
            iParameterDao.updateFunc(parameter);
            parameterList = iParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
        
    }
    
    
    public void deleteParameter(Parameter parameter)
    {
        try
        {
            iParameterDao.deleteFunc(parameter.getId());
            parameterList = iParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
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

    public Long getParameterId2() {
        return parameterId2;
    }

    public void setParameterId2(Long parameterId2) {
        this.parameterId2 = parameterId2;
    }

    public Long getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Long categoryId2) {
        this.categoryId2 = categoryId2;
    }
    
}
