package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.Inventory;
import com.mustafaderyol.inventory.IDao.IInventoryDao;
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
public class InventoryDao implements IInventoryDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Inventory object) {
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
    public List<Inventory> allFunc() {
        Query request = em.createNamedQuery("allInventory");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Inventory.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Inventory object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Inventory findByIdFunc(Long id) {
        
        Inventory p = new Inventory();
        try
        {
            p =em.find(Inventory.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
