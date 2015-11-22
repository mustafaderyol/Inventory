package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.Category;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface ICategoryDao {
    public void saveFunc(Category object);
    public List<Category> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Category object);
    public Category findByIdFunc(Long id);
    
}
