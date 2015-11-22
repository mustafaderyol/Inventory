package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.Unit;
import com.mustafaderyol.inventory.idao.IUnitDao;
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
public class UnitDao implements IUnitDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Unit object) {
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
    public List<Unit> allFunc() {
        Query request = em.createNamedQuery("allUnit");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Unit.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Unit object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Unit findByIdFunc(Long id) {
        
        Unit p = new Unit();
        try
        {
            p =em.find(Unit.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
