package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.Inventory;
import com.mustafaderyol.inventory.Entity.Services;
import com.mustafaderyol.inventory.IDao.IServicesDao;
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
public class ServicesDao implements IServicesDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Services object) {
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
    public List<Services> allServicesByInventory(Inventory inventory) {
        Query request = em.createNamedQuery("allServicesByInventory").setParameter("inventory", inventory);
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Services.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Services object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Services findByIdFunc(Long id) {
        
        Services p = new Services();
        try
        {
            p =em.find(Services.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
