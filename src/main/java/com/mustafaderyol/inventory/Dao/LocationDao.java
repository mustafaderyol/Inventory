package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.Location;
import com.mustafaderyol.inventory.IDao.ILocationDao;
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
public class LocationDao implements ILocationDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Location object) {
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
    public List<Location> allFunc() {
        Query request = em.createNamedQuery("allLocation");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Location.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Location object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Location findByIdFunc(Long id) {
        
        Location p = new Location();
        try
        {
            p =em.find(Location.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
