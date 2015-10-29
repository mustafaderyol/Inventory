/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mustafaderyol.inventory.IDao;

import com.mustafaderyol.inventory.Entity.Personal;
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

