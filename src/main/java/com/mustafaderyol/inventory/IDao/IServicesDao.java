package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Services;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IServicesDao {
    public void saveFunc(Services object);
    public List<Services> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Services object);
    public Services findByIdFunc(Long id);
    
}
