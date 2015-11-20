package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.InventoryDetailCommonParameter;
import com.mustafaderyol.inventory.IDao.IInventoryDetailCommonParameterDao;
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
public class InventoryDetailCommonParameterDao implements IInventoryDetailCommonParameterDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public InventoryDetailCommonParameter saveFunc(InventoryDetailCommonParameter object) {
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
    public List<InventoryDetailCommonParameter> allFunc() {
        Query request = em.createNamedQuery("allInventoryDetailCommonParameter");
        return request.getResultList();
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(InventoryDetailCommonParameter.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(InventoryDetailCommonParameter object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public InventoryDetailCommonParameter findByIdFunc(Long id) {
        
        InventoryDetailCommonParameter p = new InventoryDetailCommonParameter();
        try
        {
            p =em.find(InventoryDetailCommonParameter.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
