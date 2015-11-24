package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Inventory;
import com.mustafaderyol.inventory.entity.InventoryRelationship;
import com.mustafaderyol.inventory.idao.IInventoryDao;
import com.mustafaderyol.inventory.idao.IInventoryRelationshipDao;
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
@RequestMapping("/inventoryrelationship")
public class InventoryRelationshipResource {
    
    @Autowired
    private IInventoryRelationshipDao iInventoryRelationshipDao;
    
    @Autowired
    private IInventoryDao iInventoryDao;

    @RequestMapping(value = "/left/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<InventoryRelationship> getInventoryRelationshipLeft(@PathVariable Long id) {
        Inventory inventory = iInventoryDao.findByIdFunc(id);
        final List<InventoryRelationship> inventoryRelationships = iInventoryRelationshipDao.allFuncLeft(inventory);
        return inventoryRelationships;
    }

    @RequestMapping(value = "/right/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<InventoryRelationship> getInventoryRelationshipRight(@PathVariable Long id) {
        Inventory inventory = iInventoryDao.findByIdFunc(id);
        final List<InventoryRelationship> inventoryRelationships = iInventoryRelationshipDao.allFuncRight(inventory);
        return inventoryRelationships;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryRelationship getFindById(@PathVariable Long id)
    {
        InventoryRelationship inventoryRelationship = iInventoryRelationshipDao.findByIdFunc(id);
        return inventoryRelationship;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteInventoryRelationship(@PathVariable Long id)
    {
        iInventoryRelationshipDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryRelationship createInventoryRelationship(@RequestBody InventoryRelationship inventoryRelationship)
    {
        iInventoryRelationshipDao.saveFunc(inventoryRelationship);
        return inventoryRelationship;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryRelationship updateInventoryRelationship(@RequestBody InventoryRelationship inventoryRelationship)
    {
        iInventoryRelationshipDao.updateFunc(inventoryRelationship);
        return inventoryRelationship;
    }

}
