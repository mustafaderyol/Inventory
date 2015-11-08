package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Authority;
import com.mustafaderyol.inventory.Entity.GroupAuthority;
import com.mustafaderyol.inventory.Entity.GroupEntity;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IGroupAuthorityDao {
    
    public void saveFunc(GroupAuthority object);
    public List<GroupAuthority> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(GroupAuthority object);
    public GroupAuthority findByIdFunc(Long id);
    public List<GroupAuthority> allByGroup(GroupEntity group);
}
