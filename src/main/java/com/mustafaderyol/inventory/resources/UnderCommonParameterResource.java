package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.CommonParameter;
import com.mustafaderyol.inventory.entity.UnderCommonParameter;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import com.mustafaderyol.inventory.idao.ICommonParameterDao;
import com.mustafaderyol.inventory.idao.IUnderCommonParameterDao;
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
@RequestMapping("/undercommonparameter")
public class UnderCommonParameterResource {
    
    @Autowired
    IUnderCommonParameterDao iUnderCommonParameterDao;
    
    @Autowired
    ICategoryDao iCategoryDao;
    
    @Autowired
    ICommonParameterDao iCommonParameterDao;
    
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<UnderCommonParameter> getUnderCommonParameter() {
        final List<UnderCommonParameter> units = iUnderCommonParameterDao.allFunc();
        return units;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UnderCommonParameter getFindById(@PathVariable Long id)
    {
        UnderCommonParameter underCommonParameter = iUnderCommonParameterDao.findByIdFunc(id);
        return underCommonParameter;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteUnderCommonParameter(@PathVariable Long id)
    {
        iUnderCommonParameterDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UnderCommonParameter createUnderCommonParameter(@RequestBody UnderCommonParameter underCommonParameter)
    {
        iUnderCommonParameterDao.saveFunc(underCommonParameter);
        return underCommonParameter;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UnderCommonParameter updateUnderCommonParameter(@RequestBody UnderCommonParameter underCommonParameter)
    {
        iUnderCommonParameterDao.updateFunc(underCommonParameter);
        return underCommonParameter;
    }
    
    
    @RequestMapping(value = "/bycategoryid/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<UnderCommonParameter> allUnderCommonParameterByCategory(@PathVariable Long id) {
        Category category = iCategoryDao.findByIdFunc(id);
        final List<UnderCommonParameter> underCommonParameter = iUnderCommonParameterDao.allUnderCommonParameterByCategory(category);
        return underCommonParameter;
    }
    
    
    @RequestMapping(value = "/bycommonparameterid/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<UnderCommonParameter> allUnderCommonParameterByCommonParameter(@PathVariable Long id) {
        CommonParameter commonParameter = iCommonParameterDao.findByIdFunc(id);
        final List<UnderCommonParameter> underCommonParameter = iUnderCommonParameterDao.allUnderCommonParameterByCommonParameter(commonParameter);
        return underCommonParameter;
    }
    
    
    @RequestMapping(value = "/bycommonparameteridandcategoryid/{id}/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<UnderCommonParameter> allUnderCommonParameterByCommonParameterAndCategory(@PathVariable Long id1,@PathVariable Long id2) {
        Category category = iCategoryDao.findByIdFunc(id2);
        CommonParameter commonParameter = iCommonParameterDao.findByIdFunc(id1);
        final List<UnderCommonParameter> underCommonParameter = iUnderCommonParameterDao.allUnderCommonParameterByCommonParameterAndCategory(commonParameter,category);
        return underCommonParameter;
    }
    
}
