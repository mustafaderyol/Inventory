package com.mustafaderyol.inventory.Beans;

import com.mustafaderyol.inventory.Entity.Location;
import com.mustafaderyol.inventory.IDao.ILocationDao;
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
@Component(value = "locationBean")
@Scope(value = "request")
public class LocationBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    ILocationDao iLocationDao;
    
    Location location;
    List<Location> locationList = new ArrayList<Location>();
    
    @PostConstruct
    private void postConstruct() {
        location = new Location();
        locationList = iLocationDao.allFunc();
    }
    
    public void popupRefresh(){
        location = new Location();
    }
    
    public void onRowCancel(){
        locationList = iLocationDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    

    
    public void saveLocation(){
        try
        {
            location.setStatus(true);
            iLocationDao.saveFunc(location);
            locationList = iLocationDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            location = new Location();
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
    
    public void deleteLocation(Location location)
    {
        try
        {
            iLocationDao.deleteFunc(location.getId());
            locationList = iLocationDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public void updateLocation(Location location){
        try
        {
            iLocationDao.updateFunc(location);
            locationList = iLocationDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
    
}