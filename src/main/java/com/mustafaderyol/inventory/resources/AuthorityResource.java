package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.Authority;
import com.mustafaderyol.inventory.idao.IAuthorityDao;
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
@RequestMapping("/authority")
public class AuthorityResource {

    @Autowired
    private IAuthorityDao iAuthorityDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Authority> getAuthority() {
        final List<Authority> authorities = iAuthorityDao.allFunc();
        return authorities;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Authority getFindById(@PathVariable Long id)
    {
        Authority authority = iAuthorityDao.findByIdFunc(id);
        return authority;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteAuthority(@PathVariable Long id)
    {
        iAuthorityDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Authority createAuthority(@RequestBody Authority authority)
    {
        iAuthorityDao.saveFunc(authority);
        return authority;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Authority updateAuthority(@RequestBody Authority authority)
    {
        iAuthorityDao.updateFunc(authority);
        return authority;
    }

}
