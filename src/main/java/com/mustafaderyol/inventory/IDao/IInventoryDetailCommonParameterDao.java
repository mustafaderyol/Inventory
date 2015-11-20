package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.InventoryDetailCommonParameter;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IInventoryDetailCommonParameterDao {
    
    public InventoryDetailCommonParameter saveFunc(InventoryDetailCommonParameter object);
    public List<InventoryDetailCommonParameter> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(InventoryDetailCommonParameter object);
    public InventoryDetailCommonParameter findByIdFunc(Long id);
}
