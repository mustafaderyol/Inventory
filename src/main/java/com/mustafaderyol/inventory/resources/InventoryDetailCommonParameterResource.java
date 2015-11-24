package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.InventoryDetailCommonParameter;
import com.mustafaderyol.inventory.idao.IInventoryDetailCommonParameterDao;
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
@RequestMapping("/inventorydetailcommonparameter")
public class InventoryDetailCommonParameterResource {
    

    @Autowired
    private IInventoryDetailCommonParameterDao iInventoryDetailCommonParameterDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<InventoryDetailCommonParameter> getInventoryDetailCommonParameter() {
        final List<InventoryDetailCommonParameter> inventoryDetailCommonParameters = iInventoryDetailCommonParameterDao.allFunc();
        return inventoryDetailCommonParameters;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryDetailCommonParameter getFindById(@PathVariable Long id)
    {
        InventoryDetailCommonParameter inventoryDetailCommonParameter = iInventoryDetailCommonParameterDao.findByIdFunc(id);
        return inventoryDetailCommonParameter;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteInventoryDetailCommonParameter(@PathVariable Long id)
    {
        iInventoryDetailCommonParameterDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryDetailCommonParameter createInventoryDetailCommonParameter(@RequestBody InventoryDetailCommonParameter inventoryDetailCommonParameter)
    {
        inventoryDetailCommonParameter = iInventoryDetailCommonParameterDao.saveFunc(inventoryDetailCommonParameter);
        return inventoryDetailCommonParameter;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public InventoryDetailCommonParameter updateInventoryDetailCommonParameter(@RequestBody InventoryDetailCommonParameter inventoryDetailCommonParameter)
    {
        iInventoryDetailCommonParameterDao.updateFunc(inventoryDetailCommonParameter);
        return inventoryDetailCommonParameter;
    }

}
