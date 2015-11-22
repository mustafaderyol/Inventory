package com.mustafaderyol.inventory.idao;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.Parameter;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IParameterDao {
    public void saveFunc(Parameter object);
    public List<Parameter> allFunc();
    public List<Parameter> allFuncByCategory(Category category);
    public List<Parameter> allFuncByParentParameter(Parameter parameter);
    public List<Parameter> allFuncByParentParameterByCategory(Parameter parameter,Category category);
    public void deleteFunc(Long id);
    public void updateFunc(Parameter object);
    public Parameter findByIdFunc(Long id);
}
