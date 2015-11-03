package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Group;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IGroupDao {
    
    public void saveFunc(Group object);
    public List<Group> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Group object);
    public Group findByIdFunc(Long id);
}
