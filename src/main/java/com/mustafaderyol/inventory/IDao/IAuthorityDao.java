package com.mustafaderyol.inventory.IDao;
import com.mustafaderyol.inventory.Entity.Authority;
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
