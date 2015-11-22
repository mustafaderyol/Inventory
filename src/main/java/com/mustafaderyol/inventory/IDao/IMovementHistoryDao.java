package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.MovementHistory;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IMovementHistoryDao {
    public MovementHistory saveFunc(MovementHistory object);
    public List<MovementHistory> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(MovementHistory object);
    public MovementHistory findByIdFunc(Long id);
}
