package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Category;
import com.mustafaderyol.inventory.Entity.Inventory;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IInventoryDao {
    public Inventory saveFunc(Inventory object);
    public List<Inventory> allFunc();
    public List<Inventory> allInventoryByCategoryId(Category category);
    public void deleteFunc(Long id);
    public void updateFunc(Inventory object);
    public Inventory findByIdFunc(Long id);
    
}
