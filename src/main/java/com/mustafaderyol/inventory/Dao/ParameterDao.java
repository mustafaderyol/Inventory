package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.entity.Parameter;
import com.mustafaderyol.inventory.idao.IParameterDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MstfDryl
 */
@Repository
@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
public class ParameterDao implements IParameterDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Parameter object) {
        try
        {
            em.persist(object);
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public List<Parameter> allFunc() {
        Query request = em.createNamedQuery("allParameter");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Parameter.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Parameter object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Parameter findByIdFunc(Long id) {
        
        Parameter p = new Parameter();
        try
        {
            p =em.find(Parameter.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }

    @Override
    public List<Parameter> allFuncByCategory(Category category) {
        
        Query request = em.createNamedQuery("allParameterByCategory").setParameter("category", category);
        return request.getResultList();
    }

    @Override
    public List<Parameter> allFuncByParentParameter(Parameter parameter) {
        Query request = em.createNamedQuery("allParameterByParentParameter").setParameter("parentparameter", parameter);
        return request.getResultList();
    }

    @Override
    public List<Parameter> allFuncByParentParameterByCategory(Parameter parameter, Category category) {
        Query request = em.createNamedQuery("allParameterByParentParameterByCategory").setParameter("parentparameter", parameter).setParameter("category", category);
        return request.getResultList();
    }
    
}
