package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Inventory;
import com.mustafaderyol.inventory.entity.Services;
import com.mustafaderyol.inventory.idao.IInventoryDao;
import com.mustafaderyol.inventory.idao.IServicesDao;
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
@RequestMapping("/services")
public class ServicesResource {
    
    @Autowired
    IServicesDao iServicesDao;
    
    @Autowired
    IInventoryDao iInventoryDao;
    
    @RequestMapping(value = "/all/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Services> getService(@PathVariable Long id) {
        Inventory i = iInventoryDao.findByIdFunc(id);
        final List<Services> services = iServicesDao.allServicesByInventory(i);
        return services;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Services getFindById(@PathVariable Long id)
    {
        Services services = iServicesDao.findByIdFunc(id);
        return services;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteService(@PathVariable Long id)
    {
        iServicesDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Services createUnit(@RequestBody Services services)
    {
        iServicesDao.saveFunc(services);
        return services;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Services updateUnit(@RequestBody Services services)
    {
        iServicesDao.updateFunc(services);
        return services;
    }
}
