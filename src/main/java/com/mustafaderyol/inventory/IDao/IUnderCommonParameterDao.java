package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.CommonParameter;
import com.mustafaderyol.inventory.entity.UnderCommonParameter;
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
    public List<UnderCommonParameter> allUnderCommonParameterByCategory(Category category);
    public List<UnderCommonParameter> allUnderCommonParameterByCommonParameter(CommonParameter commonparameter);
    public List<UnderCommonParameter> allUnderCommonParameterByCommonParameterAndCategory(CommonParameter commonparameter,Category category);
}
