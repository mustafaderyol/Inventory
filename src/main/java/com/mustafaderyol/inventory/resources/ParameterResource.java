package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Parameter;
import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.IParameterDao;
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
@RequestMapping("/parameter")
public class ParameterResource {
    
    @Autowired
    IParameterDao iParameterDao;
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Parameter> getParameter() {
        final List<Parameter> units = iParameterDao.allFunc();
        return units;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Parameter getFindById(@PathVariable Long id)
    {
        Parameter parameter = iParameterDao.findByIdFunc(id);
        return parameter;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteUnderCommonParameter(@PathVariable Long id)
    {
        iParameterDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Parameter createUnderCommonParameter(@RequestBody Parameter parameter)
    {
        iParameterDao.saveFunc(parameter);
        return parameter;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Parameter updateUnderCommonParameter(@RequestBody Parameter parameter)
    {
        iParameterDao.updateFunc(parameter);
        return parameter;
    }
    
    
    @RequestMapping(value = "/bycategoryid/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Parameter> allFuncByCategory(@PathVariable Long id) {
        Category category = iCategoryDao.findByIdFunc(id);
        final List<Parameter> units = iParameterDao.allFuncByCategory(category);
        return units;
    }
    
    
    @RequestMapping(value = "/byparameterid/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Parameter> allFuncByParentParameter(@PathVariable Long id) {
        Parameter parameter = iParameterDao.findByIdFunc(id);
        final List<Parameter> parameters = iParameterDao.allFuncByParentParameter(parameter);
        return parameters;
    }
    
    
    @RequestMapping(value = "/byparameteridandcategoryid/{id}/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Parameter> allFuncByParentParameterByCategory(@PathVariable Long id1,@PathVariable Long id2) {
        Parameter parameter = iParameterDao.findByIdFunc(id1);
        Category category = iCategoryDao.findByIdFunc(id2);
        final List<Parameter> parameters = iParameterDao.allFuncByParentParameterByCategory(parameter,category);
        return parameters;
    }
    
}
