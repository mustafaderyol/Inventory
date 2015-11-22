package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.Inventory;
import com.mustafaderyol.inventory.entity.Services;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IServicesDao {
    public void saveFunc(Services object);
    public List<Services> allServicesByInventory(Inventory inventory);
    public void deleteFunc(Long id);
    public void updateFunc(Services object);
    public Services findByIdFunc(Long id);
    
}
