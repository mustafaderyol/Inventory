package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.Personal;
import com.mustafaderyol.inventory.idao.IPersonalDao;
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
public class PersonalDao implements IPersonalDao{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Personal object) {
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
    public List<Personal> allFunc() {
        Query request = em.createNamedQuery("allPersonal");
        return request.getResultList();
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Personal.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Personal object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Personal findByIdFunc(Long id) {
        Personal p = new Personal();
        try
        {
            p =em.find(Personal.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
