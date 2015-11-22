/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mustafaderyol.inventory.beans;

import com.mustafaderyol.inventory.entity.Unit;
import com.mustafaderyol.inventory.idao.IUnitDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MstfDryl
 */
@Component(value = "unitBean")
@Scope(value = "request")
public class UnitBean {
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    @Autowired
    IUnitDao iUnitDao;
    
    TreeNode root;
    Unit unit;
    Long unitId;
    List<Unit> unitList = new ArrayList<Unit>();
    
    
    @PostConstruct
    private void postConstruct() {
        unit = new Unit();
        unitList = iUnitDao.allFunc();
    }
    
    public void popupRefresh(){
        unit = new Unit();
        unitList = iUnitDao.allFunc();
    }
    
    public void onRowCancel(){
        unitList = iUnitDao.allFunc();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BİLGİ", "Güncelleme İşlemi İptal Edildi!"));
    }    
    
     public void saveUnitFunc(){
        try
        {
            unit.setStatus(true);
            unit.setParentunit(iUnitDao.findByIdFunc(this.unitId));
            iUnitDao.saveFunc(unit);
            unitList = iUnitDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
            unit = new Unit();
          
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!") );
        }
    }
     
    public void updateUnit(Unit unit)
    {
        try
        {
            unit.setParentunit(iUnitDao.findByIdFunc(this.unitId));
            iUnitDao.updateFunc(unit);
            unitList = iUnitDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
        
    }
    
    
    public void deleteUnit(Unit unit)
    {
        try
        {
            iUnitDao.deleteFunc(unit.getId());
            unitList = iUnitDao.allFunc();
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BİLGİ", "İşlem Başarılı") );
           
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "İşlem Başarısız!"+e) );
        }
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
}
