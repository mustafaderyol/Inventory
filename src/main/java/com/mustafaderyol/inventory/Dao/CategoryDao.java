package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.Category;
import com.mustafaderyol.inventory.idao.ICategoryDao;
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
public class CategoryDao implements ICategoryDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Category object) {
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
    public List<Category> allFunc() {
        Query request = em.createNamedQuery("allCategory");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Category.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Category object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Category findByIdFunc(Long id) {
        
        Category p = new Category();
        try
        {
            p =em.find(Category.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
