package com.mustafaderyol.inventory.resources;

/**
 * Created by usta on 22.11.2015.
 */

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.idao.ICategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/* Component anotasyonu yönetimli nesne anlamına gelmektedir */
@Controller
@RequestMapping("/category")
public class CategoryResource {

    /* Yönetimli Nesne */
    /*
      *Autowired annotationu bir Bean nin icine uygun tipteki bilgiyi otamatik olarak yüklemekte kullanilir.
    */
    @Autowired
    private ICategoryDao iCategoryDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Category> getCategory() {
        final List<Category> categories = iCategoryDao.allFunc();

        return categories;

    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Category getFindById(@PathVariable Long id)
    {
        Category category = iCategoryDao.findByIdFunc(id);
        return category;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteCategory(@PathVariable Long id)
    {
        iCategoryDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create/", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Category createCategory(@RequestBody Category category)
    {
        iCategoryDao.saveFunc(category);
        return category;
    }

    
    @RequestMapping(value = "/update/", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Category updateCategory(@RequestBody Category category)
    {
        iCategoryDao.updateFunc(category);
        return category;
    }

}