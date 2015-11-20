package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Inventory;
import com.mustafaderyol.inventory.Entity.InventoryRelationship;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IInventoryRelationshipDao {
    public void saveFunc(InventoryRelationship object);
    public List<InventoryRelationship> allFuncLeft(Inventory inventory);
    public List<InventoryRelationship> allFuncRight(Inventory inventory);
    public void deleteFunc(Long id);
    public void updateFunc(InventoryRelationship object);
    public InventoryRelationship findByIdFunc(Long id);
}
