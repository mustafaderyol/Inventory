package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.MovementHistory;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IMovementHistoryDao {
    public void saveFunc(MovementHistory object);
    public List<MovementHistory> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(MovementHistory object);
    public MovementHistory findByIdFunc(Long id);
}
