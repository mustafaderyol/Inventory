package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.CommonParameter;
import com.mustafaderyol.inventory.idao.ICommonParameterDao;
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
@Component(value = "commonParameterBean")
@Scope(value = "request")
public class CommonParameterBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    ICommonParameterDao iCommonParameterDao;
    
    CommonParameter commonParameter;
    List<CommonParameter> commonParameterList = new ArrayList<CommonParameter>();
    
    @PostConstruct
    private void postConstruct() {
        commonParameter = new CommonParameter();
        commonParameterList = iCommonParameterDao.allFunc();
    }
    
    public void popupRefresh(){
        commonParameter = new CommonParameter();
    }
    
    public void onRowCancel(){
        commonParameterList = iCommonParameterDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    
    
    public void saveCommonParameter(){
        try
        {
            commonParameter.setStatus(true);
            iCommonParameterDao.saveFunc(commonParameter);
            commonParameterList = iCommonParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            commonParameter = new CommonParameter();
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
    
    public void deleteCommonParameter(CommonParameter category)
    {
        try
        {
            iCommonParameterDao.deleteFunc(category.getId());
            commonParameterList = iCommonParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public void updateCommonParameter(CommonParameter commonParameter){
        try
        {
            iCommonParameterDao.updateFunc(commonParameter);
            commonParameterList = iCommonParameterDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }

    public CommonParameter getCommonParameter() {
        return commonParameter;
    }

    public void setCommonParameter(CommonParameter commonParameter) {
        this.commonParameter = commonParameter;
    }

    public List<CommonParameter> getCommonParameterList() {
        return commonParameterList;
    }

    public void setCommonParameterList(List<CommonParameter> commonParameterList) {
        this.commonParameterList = commonParameterList;
    }
}
