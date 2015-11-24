package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Unit;
import com.mustafaderyol.inventory.idao.IUnitDao;
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
@RequestMapping("/unit")
public class UnitResource {
    
    @Autowired
    IUnitDao iUnitDao;
    
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Unit> getUnit() {
        final List<Unit> units = iUnitDao.allFunc();
        return units;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Unit getFindById(@PathVariable Long id)
    {
        Unit unit = iUnitDao.findByIdFunc(id);
        return unit;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteUnit(@PathVariable Long id)
    {
        iUnitDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Unit createUnit(@RequestBody Unit unit)
    {
        iUnitDao.saveFunc(unit);
        return unit;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Unit updateUnit(@RequestBody Unit unit)
    {
        iUnitDao.updateFunc(unit);
        return unit;
    }
    
}
