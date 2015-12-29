package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.Inventory;
import com.mustafaderyol.inventory.entity.InventoryDetailCommonParameter;
import com.mustafaderyol.inventory.entity.InventoryDetailParameter;
import com.mustafaderyol.inventory.entity.MovementHistory;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.IInventoryDao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MstfDryl
 */
@Controller
@RequestMapping("/inventory")
public class InventoryResource {
    

    @Autowired
    private ICategoryDao iCategoryDao;

    @Autowired
    private IInventoryDao iInventoryDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Inventory> getInventory() {
        final List<Inventory> inventories = iInventoryDao.allFunc();
        return inventories;
    }

    @RequestMapping(value = "/bycategoryid/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Inventory> getInventoryByCategory(@PathVariable Long id) {
        Category category = iCategoryDao.findByIdFunc(id);
        final List<Inventory> inventories = iInventoryDao.allInventoryByCategoryId(category);
        return inventories;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Inventory getFindById(@PathVariable Long id)
    {
        Inventory inventory = iInventoryDao.findByIdFunc(id);
        inventory.setMovementHistory(new ArrayList<MovementHistory>(new HashSet<MovementHistory>( inventory.getMovementHistory() )));
        inventory.setInventoryDetailParameter(new ArrayList<InventoryDetailParameter>(new HashSet<InventoryDetailParameter>( inventory.getInventoryDetailParameter() )));
        inventory.setInventoryDetailCommonParameter(new ArrayList<InventoryDetailCommonParameter>(new HashSet<InventoryDetailCommonParameter>( inventory.getInventoryDetailCommonParameter() )));
        return inventory;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteInventory(@PathVariable Long id)
    {
        iInventoryDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Inventory createInventory(@RequestBody Inventory inventory)
    {
        inventory = iInventoryDao.saveFunc(inventory);
        return inventory;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Inventory updateInventory(@RequestBody Inventory inventory)
    {
        iInventoryDao.updateFunc(inventory);
        return inventory;
    }

}
