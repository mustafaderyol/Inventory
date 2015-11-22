package com.mustafaderyol.inventory.idao;
import com.mustafaderyol.inventory.entity.Authority;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IAuthorityDao {
    
    public void saveFunc(Authority object);
    public List<Authority> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Authority object);
    public Authority findByIdFunc(Long id);
}
