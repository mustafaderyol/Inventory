package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.UnderCommonParameter;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IUnderCommonParameterDao {
    public void saveFunc(UnderCommonParameter object);
    public List<UnderCommonParameter> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(UnderCommonParameter object);
    public UnderCommonParameter findByIdFunc(Long id);
    
}
