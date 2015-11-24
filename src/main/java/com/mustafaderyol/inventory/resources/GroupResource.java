package com.mustafaderyol.inventory.resources;

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
@RequestMapping("/group")
public class GroupResource {
    
    @Autowired
    private IGroupDao iGroupDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<GroupEntity> getGroupEntity() {
        final List<GroupEntity> groups = iGroupDao.allFunc();
        return groups;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GroupEntity getFindById(@PathVariable Long id)
    {
        GroupEntity group = iGroupDao.findByIdFunc(id);
        return group;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteGroupEntity(@PathVariable Long id)
    {
        iGroupDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GroupEntity createGroupEntity(@RequestBody GroupEntity group)
    {
        iGroupDao.saveFunc(group);
        return group;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GroupEntity updateGroupEntity(@RequestBody GroupEntity group)
    {
        iGroupDao.updateFunc(group);
        return group;
    }
}
