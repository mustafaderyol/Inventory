package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.InventoryDetailParameter;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IInventoryDetailParameterDao {
    
    public InventoryDetailParameter saveFunc(InventoryDetailParameter object);
    public List<InventoryDetailParameter> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(InventoryDetailParameter object);
    public InventoryDetailParameter findByIdFunc(Long id);
}
