package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Location;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface ILocationDao {
    public void saveFunc(Location object);
    public List<Location> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Location object);
    public Location findByIdFunc(Long id);
}
