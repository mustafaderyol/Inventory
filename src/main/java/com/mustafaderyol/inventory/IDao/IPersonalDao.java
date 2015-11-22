package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.Personal;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IPersonalDao {
    public void saveFunc(Personal object);
    public List<Personal> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Personal object);
    public Personal findByIdFunc(Long id);
}

