package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.MovementHistory;
import com.mustafaderyol.inventory.IDao.IMovementHistoryDao;
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
public class MovementHistoryDao implements IMovementHistoryDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public MovementHistory saveFunc(MovementHistory object) {
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
    public List<MovementHistory> allFunc() {
        Query request = em.createNamedQuery("allMovementHistory");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(MovementHistory.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(MovementHistory object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public MovementHistory findByIdFunc(Long id) {
        
        MovementHistory p = new MovementHistory();
        try
        {
            p =em.find(MovementHistory.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
