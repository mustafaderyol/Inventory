package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Parameter;
import java.util.List;

/**
 *
 * @author MstfDryl
 */
public interface IParameterDao {
    public void saveFunc(Parameter object);
    public List<Parameter> allFunc();
    public void deleteFunc(Long id);
    public void updateFunc(Parameter object);
    public Parameter findByIdFunc(Long id);
}
