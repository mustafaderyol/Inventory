package com.mustafaderyol.inventory.Beans;

import com.mustafaderyol.inventory.Entity.Category;
import com.mustafaderyol.inventory.Entity.Inventory;
import com.mustafaderyol.inventory.Entity.Services;
import com.mustafaderyol.inventory.IDao.ICategoryDao;
import com.mustafaderyol.inventory.IDao.IInventoryDao;
import com.mustafaderyol.inventory.IDao.IServicesDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
@Component(value="inventoryPageBean")
@ViewScoped
public class InventoryPageBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    @Autowired
    IInventoryDao iInventoryDao;
    
    @Autowired
    IServicesDao iServicesDao;
    
    Services services,services2;
    List<Services> servicesList = new ArrayList<Services>();
    
    Category category;
    Inventory inventory;
    List<Inventory> inventoryList = new ArrayList<Inventory>();
    
    @PostConstruct
    private void postConstruct() {
        category = new Category();
        inventory = new Inventory();
        services = new Services();
        services2 = new Services();
    }
    
    public String getUrlParameter()
    {
        Map<String, String> params =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String parameterOne = params.get("category");
        this.category = iCategoryDao.findByIdFunc(Long.parseLong(parameterOne));
        return this.category.getName();
    }
    public List<Inventory> getInventoryListByCategory()
    {
        inventoryList = iInventoryDao.allInventoryByCategoryId(this.category);
        return inventoryList;
    }
    
    public String getQrCodeGenerator(Long id)
    {
        return String.valueOf(id+Long.parseLong("120000"));
    }
    
    public void inventoryDetail(Inventory inventory)
    {
         this.inventory = inventory;
         
        servicesList = iServicesDao.allServicesByInventory(this.inventory);
    }
    
    public void deleteInventory(Inventory inventory)
    {
        try
        {
            iInventoryDao.deleteFunc(inventory.getId());
            inventoryList = iInventoryDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }
    
    public void selectServices(Services services)
    {
        this.services = services;
    }
    
    public void closeServices(Services services)
    {
        Date date = new Date();
        services.setComeDate(date);
        iServicesDao.updateFunc(services);
        servicesList = iServicesDao.allServicesByInventory(this.inventory);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
    }
    
    public void saveServices()
    {
        Date date = new Date();
        this.services2.setGoDate(date);
        this.services2.setInventory(this.inventory);
        this.services2.setMovementhistory(this.inventory.getMovementHistory().get(this.inventory.getMovementHistory().size()-1));
        this.services2.setStatus(Boolean.TRUE);
        iServicesDao.saveFunc(this.services2);
        servicesList = iServicesDao.allServicesByInventory(this.inventory);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
        this.services2 = new Services();
    }
    
    
    
    
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public List<Services> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    public Services getServices2() {
        return services2;
    }

    public void setServices2(Services services2) {
        this.services2 = services2;
    }
}
