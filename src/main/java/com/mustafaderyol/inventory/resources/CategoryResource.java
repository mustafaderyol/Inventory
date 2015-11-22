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

/* Component anotasyonu yönetimli nesne anlamına gelmektedir */
@Controller
public class CategoryResource {

    /* Yönetimli Nesne */
    /*
      *Autowired annotationu bir Bean nin icine uygun tipteki bilgiyi otamatik olarak yüklemekte kullanilir.
    */
    @Autowired
    private ICategoryDao iCategoryDao;

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> getCategory() {
        final List<Category> categories = iCategoryDao.allFunc();

        return categories;

    }


}