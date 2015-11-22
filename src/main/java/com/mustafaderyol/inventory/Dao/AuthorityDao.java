package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.Authority;
import com.mustafaderyol.inventory.idao.IAuthorityDao;
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
public class AuthorityDao implements IAuthorityDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Authority object) {
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
    public List<Authority> allFunc() {
        Query request = em.createNamedQuery("allAuthority");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Authority.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Authority object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Authority findByIdFunc(Long id) {
        
        Authority p = new Authority();
        try
        {
            p =em.find(Authority.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
