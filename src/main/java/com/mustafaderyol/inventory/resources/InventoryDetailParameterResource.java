package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.InventoryDetailParameter;
import com.mustafaderyol.inventory.idao.IInventoryDetailParameterDao;
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
@RequestMapping("/inventorydetailparameter")
public class InventoryDetailParameterResource {
    
    @Autowired
    private IInventoryDetailParameterDao iInventoryDetailParameterDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<InventoryDetailParameter> getInventoryDetailParameter() {
        final List<InventoryDetailParameter> inventoryDetailParameters = iInventoryDetailParameterDao.allFunc();
        return inventoryDetailParameters;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryDetailParameter getFindById(@PathVariable Long id)
    {
        InventoryDetailParameter inventoryDetailParameter = iInventoryDetailParameterDao.findByIdFunc(id);
        return inventoryDetailParameter;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteInventoryDetailParameter(@PathVariable Long id)
    {
        iInventoryDetailParameterDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryDetailParameter createInventoryDetailParameter(@RequestBody InventoryDetailParameter inventoryDetailParameter)
    {
        inventoryDetailParameter = iInventoryDetailParameterDao.saveFunc(inventoryDetailParameter);
        return inventoryDetailParameter;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryDetailParameter updateinventoryDetailParameter(@RequestBody InventoryDetailParameter inventoryDetailParameter)
    {
        iInventoryDetailParameterDao.updateFunc(inventoryDetailParameter);
        return inventoryDetailParameter;
    }

}
