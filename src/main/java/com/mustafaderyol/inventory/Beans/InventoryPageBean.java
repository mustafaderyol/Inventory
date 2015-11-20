package com.mustafaderyol.inventory.Beans;

import com.mustafaderyol.inventory.Entity.Category;
import com.mustafaderyol.inventory.Entity.Inventory;
import com.mustafaderyol.inventory.IDao.ICategoryDao;
import com.mustafaderyol.inventory.IDao.IInventoryDao;
import java.util.ArrayList;
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
    
    Category category;
    Inventory inventory;
    List<Inventory> inventoryList = new ArrayList<Inventory>();
    
    @PostConstruct
    private void postConstruct() {
        category = new Category();
        inventory = new Inventory();
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
}
