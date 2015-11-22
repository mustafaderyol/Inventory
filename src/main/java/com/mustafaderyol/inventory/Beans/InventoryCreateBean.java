package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.CommonParameter;
import com.mustafaderyol.inventory.entity.Inventory;
import com.mustafaderyol.inventory.entity.InventoryDetailCommonParameter;
import com.mustafaderyol.inventory.entity.InventoryDetailParameter;
import com.mustafaderyol.inventory.entity.Location;
import com.mustafaderyol.inventory.entity.MovementHistory;
import com.mustafaderyol.inventory.entity.Parameter;
import com.mustafaderyol.inventory.entity.Personal;
import com.mustafaderyol.inventory.entity.UnderCommonParameter;
import com.mustafaderyol.inventory.entity.Unit;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.ICommonParameterDao;
import com.mustafaderyol.inventory.idao.IInventoryDao;
import com.mustafaderyol.inventory.idao.IInventoryDetailCommonParameterDao;
import com.mustafaderyol.inventory.idao.IInventoryDetailParameterDao;
import com.mustafaderyol.inventory.idao.ILocationDao;
import com.mustafaderyol.inventory.idao.IMovementHistoryDao;
import com.mustafaderyol.inventory.idao.IParameterDao;
import com.mustafaderyol.inventory.idao.IPersonalDao;
import com.mustafaderyol.inventory.idao.IUnderCommonParameterDao;
import com.mustafaderyol.inventory.idao.IUnitDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
@Component(value="inventoryCreateBean")
@ViewScoped
public class InventoryCreateBean {
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    @Autowired
    IPersonalDao iPersonalDao;
    
    @Autowired
    ILocationDao iLocationDao;
    
    @Autowired
    IUnitDao iUnitDao;
    
    @Autowired
    IParameterDao iParameterDao;
    
    @Autowired
    ICommonParameterDao iCommonParameterDao;
    
    @Autowired
    IUnderCommonParameterDao iUnderCommonParameterDao;
    
    @Autowired
    IMovementHistoryDao iMovementHistoryDao; 
    
    @Autowired
    IInventoryDetailCommonParameterDao iInventoryDetailCommonParameterDao;
    
    @Autowired
    IInventoryDetailParameterDao iInventoryDetailParameterDao;
    
    @Autowired
    IInventoryDao iInventoryDao;
    
    Category category;
    
    MovementHistory movementHistory;
    
    Inventory inventory;
    
    List<Personal> personalList = new ArrayList<Personal>();
    Long personalId;
    
    List<Location> locationList = new ArrayList<Location>();
    Long locationId;
    
    List<Unit> unitList = new ArrayList<Unit>();
    Long unitId;
    
    List<Parameter> parameterList = new ArrayList<Parameter>();
    Map<Long,Long> mParameter = new HashMap<Long,Long>();
    Map<Long,String> mParameterString = new HashMap<Long,String>();
    Long parameterId;
    
    
    List<CommonParameter> commonParameterList = new ArrayList<CommonParameter>();
    Map<Long,Long> mCommonParameter = new HashMap<Long,Long>();
    Long commonParameterId;
    
    List<InventoryDetailParameter> inventoryDetailParameterList = new ArrayList<InventoryDetailParameter>();
    List<InventoryDetailCommonParameter> inventoryDetailCommonParameterList = new ArrayList<InventoryDetailCommonParameter>();
    
    List<Inventory> inventoryList = new ArrayList<Inventory>();
    
    String temp,locationDetail,note;
    
    @PostConstruct
    private void postConstruct() {
        category = new Category();
        movementHistory = new MovementHistory();
        inventory = new Inventory();
        personalList = iPersonalDao.allFunc();
        locationList = iLocationDao.allFunc();
        unitList    =iUnitDao.allFunc();
        commonParameterList = iCommonParameterDao.allFunc();
        inventoryDetailParameterList = new ArrayList<InventoryDetailParameter>();
        inventoryDetailCommonParameterList = new ArrayList<InventoryDetailCommonParameter>();
    }
    
    
    public String getUrlParameter()
    {
        Map<String, String> params =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String parameterOne = params.get("category");
        this.category = iCategoryDao.findByIdFunc(Long.parseLong(parameterOne));
        parameterList = iParameterDao.allFuncByCategory(category);
        this.inventory.setCategory(category);
        return this.category.getName();
    }
    
    public List<Inventory> getInventoryListByCategory()
    {
        inventoryList = iInventoryDao.allInventoryByCategoryId(this.category);
        return inventoryList;
    }
    
    public List commonParameterGetList(CommonParameter commonParameter)
    {
        List<UnderCommonParameter> underCommonParameterList = new ArrayList<UnderCommonParameter>();
        underCommonParameterList = iUnderCommonParameterDao.allUnderCommonParameterByCommonParameterAndCategory(commonParameter,this.category);
        return underCommonParameterList;
    }
    
    public List parameterGetList(Parameter parameter)
    {
        List<Parameter> underParameterList = new ArrayList<Parameter>();
        underParameterList = iParameterDao.allFuncByParentParameterByCategory(parameter,this.category);
        return underParameterList;
    }
    
    public void commonParameterChangeSelect(Long selectOneMenuId)
    {
        UnderCommonParameter ucp = new UnderCommonParameter();
        ucp = iUnderCommonParameterDao.findByIdFunc(this.commonParameterId);
        this.mCommonParameter.put(selectOneMenuId, ucp.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", ucp.getName()+" seçildi."));
    } 
    
    public void parameterChangeSelect(Long selectOneMenuId)
    {
        Parameter p = new Parameter();
        p = iParameterDao.findByIdFunc(this.parameterId);
        this.mParameter.put(selectOneMenuId, p.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", p.getName()+" seçildi."));
    } 
    
    public void onBlurMethod(Long inputTextId)
    {
        this.mParameterString.put(inputTextId, this.temp);
    }
    
    
    public void saveInventory()
    {
        try
        {
            Date date = new Date();
            
            this.inventory.setNote(note);
            
            this.movementHistory.setCreatedDate(date);
            this.movementHistory.setLocation(iLocationDao.findByIdFunc(this.locationId));
            this.movementHistory.setPersonal(iPersonalDao.findByIdFunc(this.personalId));
            this.movementHistory.setUnit(iUnitDao.findByIdFunc(this.unitId));
            this.movementHistory.setLocationdetail(locationDetail);
            this.movementHistory.setStatus(Boolean.TRUE);
            movementHistory = iMovementHistoryDao.saveFunc(movementHistory);
            
            List<MovementHistory> mhlist = new ArrayList<MovementHistory>(); 
            mhlist.add(movementHistory);
            
            
            for(int i = 0; i < this.commonParameterList.size(); i++ )
            {
                InventoryDetailCommonParameter idcp = new InventoryDetailCommonParameter();
                Long id = this.mCommonParameter.get(this.commonParameterList.get(i).getId());
                
                
                idcp.setCommonparameter(this.commonParameterList.get(i));
                if(id != null)
                {
                    UnderCommonParameter ucp = iUnderCommonParameterDao.findByIdFunc(id);
                    idcp.setUndercommonparameter(ucp);
                }
                else
                {
                    idcp.setUndercommonparameter(null);
                }
                
                idcp = iInventoryDetailCommonParameterDao.saveFunc(idcp);
                this.inventoryDetailCommonParameterList.add(idcp);
            }
            
            for(int i = 0; i < this.parameterList.size(); i++ )
            {
                String s = null;
                InventoryDetailParameter idp = new InventoryDetailParameter();
                
                Parameter p = iParameterDao.findByIdFunc(this.parameterList.get(i).getId());
               
                if(!this.parameterList.get(i).getSelectortext())
                {
                    idp.setParameter(this.parameterList.get(i));
                    p = iParameterDao.findByIdFunc(mParameter.get(p.getId()));
                    idp.setAnswerparameter(p);
                    idp.setText(null);
                    
                }
                else
                {
                    idp.setParameter(this.parameterList.get(i));
                    idp.setAnswerparameter(null);
                    idp.setText(this.mParameterString.get(this.parameterList.get(i).getId()));
                       
                }
                idp = iInventoryDetailParameterDao.saveFunc(idp);
                this.inventoryDetailParameterList.add(idp);
            }
            
           
            
            this.inventory = iInventoryDao.saveFunc(this.inventory);
            
            this.inventory.setInventoryDetailParameter(this.inventoryDetailParameterList);
            this.inventory.setInventoryDetailCommonParameter(this.inventoryDetailCommonParameterList);
            this.inventory.setMovementHistory(mhlist);
            
            iInventoryDao.updateFunc(this.inventory);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Başarılı"));
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Başarısız"+e));
    
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

    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
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

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }

    public List<CommonParameter> getCommonParameterList() {
        return commonParameterList;
    }

    public void setCommonParameterList(List<CommonParameter> commonParameterList) {
        this.commonParameterList = commonParameterList;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public Long getCommonParameterId() {
        return commonParameterId;
    }

    public void setCommonParameterId(Long commonParameterId) {
        this.commonParameterId = commonParameterId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
