package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.CommonParameter;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface ICommonParameterDao {
    public void saveFunc(CommonParameter object);
    public List<CommonParameter> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(CommonParameter object);
    public CommonParameter findByIdFunc(Long id);
    
}
