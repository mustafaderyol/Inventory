package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Personal;
import com.mustafaderyol.inventory.idao.IPersonalDao;
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
@RequestMapping("/personal")
public class PersonalResource {
    
    @Autowired
    IPersonalDao iPersonalDao;
    
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Personal> getPersonal() {
        final List<Personal> personals = iPersonalDao.allFunc();
        return personals;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Personal getFindById(@PathVariable Long id)
    {
        Personal personal = iPersonalDao.findByIdFunc(id);
        return personal;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deletePersonal(@PathVariable Long id)
    {
        iPersonalDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Personal createPersonal(@RequestBody Personal personal)
    {
        iPersonalDao.saveFunc(personal);
        return personal;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Personal updatePersonal(@RequestBody Personal personal)
    {
        iPersonalDao.updateFunc(personal);
        return personal;
    }
    
}
