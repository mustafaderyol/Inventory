package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.InventoryDetailCommonParameter;
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
