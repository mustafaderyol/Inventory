package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Unit;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IUnitDao {
    public void saveFunc(Unit object);
    public List<Unit> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Unit object);
    public Unit findByIdFunc(Long id);
}
