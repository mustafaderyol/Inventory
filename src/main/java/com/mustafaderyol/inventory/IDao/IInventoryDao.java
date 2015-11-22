package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.Inventory;
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
    public Inventory updateFunc(Inventory object);
    public Inventory findByIdFunc(Long id);
    
}
