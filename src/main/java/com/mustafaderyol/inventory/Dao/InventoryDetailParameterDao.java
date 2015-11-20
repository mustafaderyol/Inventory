package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.InventoryDetailParameter;
import com.mustafaderyol.inventory.IDao.IInventoryDetailParameterDao;
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
public class InventoryDetailParameterDao implements IInventoryDetailParameterDao {
    
    @PersistenceContext
    EntityManager em;


    @Override
    public InventoryDetailParameter saveFunc(InventoryDetailParameter object) {
        try
        {
            em.persist(object);
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return object;
    }

    @Override
    public List<InventoryDetailParameter> allFunc() {
        Query request = em.createNamedQuery("allInventoryDetailParameter");
        return request.getResultList();
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(InventoryDetailParameter.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(InventoryDetailParameter object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public InventoryDetailParameter findByIdFunc(Long id) {
        
        InventoryDetailParameter p = new InventoryDetailParameter();
        try
        {
            p =em.find(InventoryDetailParameter.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
