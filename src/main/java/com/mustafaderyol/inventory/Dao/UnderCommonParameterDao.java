package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.UnderCommonParameter;
import com.mustafaderyol.inventory.IDao.IUnderCommonParameterDao;
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
public class UnderCommonParameterDao implements IUnderCommonParameterDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(UnderCommonParameter object) {
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
    public List<UnderCommonParameter> allFunc() {
        Query request = em.createNamedQuery("allUnderCommonParameter");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(UnderCommonParameter.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(UnderCommonParameter object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public UnderCommonParameter findByIdFunc(Long id) {
        
        UnderCommonParameter p = new UnderCommonParameter();
        try
        {
            p =em.find(UnderCommonParameter.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
