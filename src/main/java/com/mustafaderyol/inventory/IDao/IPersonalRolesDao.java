package com.mustafaderyol.inventory.idao;
import com.mustafaderyol.inventory.entity.PersonalRoles;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IPersonalRolesDao {
    
    public void saveFunc(PersonalRoles object);
    public List<PersonalRoles> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(PersonalRoles object);
    public PersonalRoles findByIdFunc(Long id);
    public List<PersonalRoles> findByPersonalIdFunc(Long id);
}
