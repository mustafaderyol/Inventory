package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.GroupAuthority;
import com.mustafaderyol.inventory.idao.IGroupAuthorityDao;
import com.mustafaderyol.inventory.entity.GroupEntity;
import com.mustafaderyol.inventory.idao.IGroupDao;
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
@RequestMapping("/groupauthority")
public class GroupAuthorityResource {

    @Autowired
    private IGroupAuthorityDao iGroupAuthorityDao;

    @Autowired
    private IGroupDao iGroupDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<GroupAuthority> getGroupAuthority() {
        final List<GroupAuthority> groupAuthorities = iGroupAuthorityDao.allFunc();
        return groupAuthorities;
    }

    @RequestMapping(value = "/bygroup/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<GroupAuthority> getGroupAuthorityByGroupEntity(@PathVariable Long id) {
        GroupEntity g = iGroupDao.findByIdFunc(id);
        final List<GroupAuthority> groupAuthorities = iGroupAuthorityDao.allByGroup(g);
        return groupAuthorities;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GroupAuthority getFindById(@PathVariable Long id)
    {
        GroupAuthority groupAuthority = iGroupAuthorityDao.findByIdFunc(id);
        return groupAuthority;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteGroupAuthority(@PathVariable Long id)
    {
        iGroupAuthorityDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GroupAuthority createGroupAuthority(@RequestBody GroupAuthority groupAuthority)
    {
        iGroupAuthorityDao.saveFunc(groupAuthority);
        return groupAuthority;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GroupAuthority updateGroupAuthority(@RequestBody GroupAuthority groupAuthority)
    {
        iGroupAuthorityDao.updateFunc(groupAuthority);
        return groupAuthority;
    }
}
