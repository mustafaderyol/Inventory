package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Location;
import com.mustafaderyol.inventory.idao.ILocationDao;
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
@RequestMapping("/location")
public class LocationResource {
    
    @Autowired
    private ILocationDao iLocationDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Location> getLocation() {
        final List<Location> locations = iLocationDao.allFunc();

        return locations;

    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Location getFindById(@PathVariable Long id)
    {
        Location location = iLocationDao.findByIdFunc(id);
        return location;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteLocation(@PathVariable Long id)
    {
        iLocationDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Location createLocation(@RequestBody Location location)
    {
        iLocationDao.saveFunc(location);
        return location;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Location updateCategory(@RequestBody Location location)
    {
        iLocationDao.updateFunc(location);
        return location;
    }

}
