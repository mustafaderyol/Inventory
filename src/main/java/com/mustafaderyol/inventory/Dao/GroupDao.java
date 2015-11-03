package com.mustafaderyol.inventory.Dao;

import com.mustafaderyol.inventory.Entity.Group;
import com.mustafaderyol.inventory.IDao.IGroupDao;
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
public class GroupDao implements IGroupDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(Group object) {
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
    public List<Group> allFunc() {
        Query request = em.createNamedQuery("allGroup");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(Group.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(Group object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public Group findByIdFunc(Long id) {
        
        Group p = new Group();
        try
        {
            p =em.find(Group.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
