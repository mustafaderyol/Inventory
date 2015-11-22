package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.Inventory;
import com.mustafaderyol.inventory.entity.InventoryRelationship;
import com.mustafaderyol.inventory.entity.Location;
import com.mustafaderyol.inventory.entity.MovementHistory;
import com.mustafaderyol.inventory.entity.Personal;
import com.mustafaderyol.inventory.entity.Services;
import com.mustafaderyol.inventory.entity.Unit;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.IInventoryDao;
import com.mustafaderyol.inventory.idao.IInventoryRelationshipDao;
import com.mustafaderyol.inventory.idao.ILocationDao;
import com.mustafaderyol.inventory.idao.IMovementHistoryDao;
import com.mustafaderyol.inventory.idao.IPersonalDao;
import com.mustafaderyol.inventory.idao.IServicesDao;
import com.mustafaderyol.inventory.idao.IUnitDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
@Component(value="inventoryPageBean")
@ViewScoped
public class InventoryPageBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    @Autowired
    ILocationDao iLocationDao;
    
    @Autowired
    IPersonalDao iPersonalDao;
    
    @Autowired
    IUnitDao iUnitDao;
    
    @Autowired
    IMovementHistoryDao iMovementHistoryDao;
    
    @Autowired
    IInventoryDao iInventoryDao;
    
    @Autowired
    IServicesDao iServicesDao;
    
    @Autowired
    IInventoryRelationshipDao iInventoryRelationshipDao;
    
    InventoryRelationship inventoryRelationship;
    List<InventoryRelationship> inventoryRelationshipList1 = new ArrayList<InventoryRelationship>();
    List<InventoryRelationship> inventoryRelationshipList2 = new ArrayList<InventoryRelationship>();
    
    List<Personal> personalList = new ArrayList<Personal>();
    List<Unit> unitList = new ArrayList<Unit>();
    List<Location> locationList = new ArrayList<Location>();
    
    Services services,services2;
    List<Services> servicesList = new ArrayList<Services>();
    
    Category category;
    Inventory inventory;
    Long inventoryId;
    List<Inventory> inventoryList = new ArrayList<Inventory>();
    List<Inventory> inventoryList2 = new ArrayList<Inventory>();
    
    Long personalId,locationId,unitId;
    MovementHistory movementHistory;
    
    @PostConstruct
    private void postConstruct() {
        category = new Category();
        inventory = new Inventory();
        services = new Services();
        services2 = new Services();
        movementHistory = new MovementHistory();
        inventoryRelationship = new InventoryRelationship();
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
        personalList = iPersonalDao.allFunc();
        unitList = iUnitDao.allFunc();
        locationList = iLocationDao.allFunc();
        servicesList = iServicesDao.allServicesByInventory(this.inventory);
        inventoryRelationshipList1 = iInventoryRelationshipDao.allFuncLeft(this.inventory);
        inventoryRelationshipList2 = iInventoryRelationshipDao.allFuncRight(this.inventory);
        inventoryList2 = iInventoryDao.allFunc();
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
    
    public void saveMovementHistory()
    {
        Date date = new Date();
        this.movementHistory.setLocation(iLocationDao.findByIdFunc(this.locationId));
        this.movementHistory.setPersonal(iPersonalDao.findByIdFunc(this.personalId));
        this.movementHistory.setUnit(iUnitDao.findByIdFunc(this.unitId));
        this.movementHistory.setStatus(Boolean.TRUE);
        this.movementHistory.setCreatedDate(date);
        this.movementHistory = iMovementHistoryDao.saveFunc(this.movementHistory);
        List<MovementHistory> listTemp = this.inventory.getMovementHistory();
        listTemp.add(this.movementHistory);
        this.inventory.setMovementHistory(listTemp);
        this.inventory = iInventoryDao.updateFunc(this.inventory);
        this.inventoryList = iInventoryDao.allInventoryByCategoryId(this.category);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
        this.movementHistory = new MovementHistory();
    }
    
    public void deleteRelationship(Long id)
    {
        iInventoryRelationshipDao.deleteFunc(id);
        inventoryRelationshipList1 = iInventoryRelationshipDao.allFuncLeft(this.inventory);
        inventoryRelationshipList2 = iInventoryRelationshipDao.allFuncRight(this.inventory);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
        this.inventoryRelationship = new InventoryRelationship();
    }
    
    public void addRelationship()
    {
        this.inventoryRelationship.setInventory1(this.inventory);
        this.inventoryRelationship.setInventory2(iInventoryDao.findByIdFunc(this.inventoryId));
        iInventoryRelationshipDao.saveFunc(this.inventoryRelationship);
        inventoryRelationshipList1 = iInventoryRelationshipDao.allFuncLeft(this.inventory);
        inventoryRelationshipList2 = iInventoryRelationshipDao.allFuncRight(this.inventory);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
        this.inventoryRelationship = new InventoryRelationship();
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

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public MovementHistory getMovementHistory() {
        return movementHistory;
    }

    public void setMovementHistory(MovementHistory movementHistory) {
        this.movementHistory = movementHistory;
    }

    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public InventoryRelationship getInventoryRelationship() {
        return inventoryRelationship;
    }

    public void setInventoryRelationship(InventoryRelationship inventoryRelationship) {
        this.inventoryRelationship = inventoryRelationship;
    }

    public List<InventoryRelationship> getInventoryRelationshipList1() {
        return inventoryRelationshipList1;
    }

    public void setInventoryRelationshipList1(List<InventoryRelationship> inventoryRelationshipList1) {
        this.inventoryRelationshipList1 = inventoryRelationshipList1;
    }

    public List<InventoryRelationship> getInventoryRelationshipList2() {
        return inventoryRelationshipList2;
    }

    public void setInventoryRelationshipList2(List<InventoryRelationship> inventoryRelationshipList2) {
        this.inventoryRelationshipList2 = inventoryRelationshipList2;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public List<Inventory> getInventoryList2() {
        return inventoryList2;
    }

    public void setInventoryList2(List<Inventory> inventoryList2) {
        this.inventoryList2 = inventoryList2;
    }
}
