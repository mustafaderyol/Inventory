package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Inventory;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IInventoryDao {
    public void saveFunc(Inventory object);
    public List<Inventory> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Inventory object);
    public Inventory findByIdFunc(Long id);
    
}
