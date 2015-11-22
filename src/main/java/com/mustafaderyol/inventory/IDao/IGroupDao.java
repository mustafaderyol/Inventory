package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.GroupEntity;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IGroupDao {
    
    public void saveFunc(GroupEntity object);
    public List<GroupEntity> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(GroupEntity object);
    public GroupEntity findByIdFunc(Long id);
}
