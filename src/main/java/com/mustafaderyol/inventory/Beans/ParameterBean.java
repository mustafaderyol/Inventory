package com.mustafaderyol.inventory.Beans;

import com.mustafaderyol.inventory.Entity.Category;
import com.mustafaderyol.inventory.Entity.Parameter;
import com.mustafaderyol.inventory.IDao.ICategoryDao;
import com.mustafaderyol.inventory.IDao.IParameterDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
    Long parameterId;
    Long categoryId;
    List<Parameter> parameterList = new ArrayList<Parameter>();
    List<Category> categoryList = new ArrayList<Category>();
    
    
    @PostConstruct
    private void postConstruct() {
        parameter = new Parameter();
        parameterList  = iParameterDao.allFunc();
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
     
    public void updateParameter(Parameter paramater)
    {
        try
        {
            parameter.setCategory(iCategoryDao.findByIdFunc(categoryId));
            parameter.setParentparameter(iParameterDao.findByIdFunc(parameterId));
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
    
}
