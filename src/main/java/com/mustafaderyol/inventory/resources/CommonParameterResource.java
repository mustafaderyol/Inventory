package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.CommonParameter;
import com.mustafaderyol.inventory.idao.ICommonParameterDao;
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
@RequestMapping("/commonparameter")
public class CommonParameterResource {
    

    @Autowired
    private ICommonParameterDao iCommonParameterDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<CommonParameter> getCommonParameter() {
        final List<CommonParameter> commonParameters = iCommonParameterDao.allFunc();
        return commonParameters;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CommonParameter getFindById(@PathVariable Long id)
    {
        CommonParameter commonParameter = iCommonParameterDao.findByIdFunc(id);
        return commonParameter;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteCommonParameter(@PathVariable Long id)
    {
        iCommonParameterDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CommonParameter createCommonParameter(@RequestBody CommonParameter commonParameter)
    {
        iCommonParameterDao.saveFunc(commonParameter);
        return commonParameter;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CommonParameter updateCommonParameter(@RequestBody CommonParameter commonParameter)
    {
        iCommonParameterDao.updateFunc(commonParameter);
        return commonParameter;
    }
}
