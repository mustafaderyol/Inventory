package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.GroupEntity;
import com.mustafaderyol.inventory.idao.IGroupDao;
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
    public void saveFunc(GroupEntity object) {
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
    public List<GroupEntity> allFunc() {
        Query request = em.createNamedQuery("allGroup");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(GroupEntity.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(GroupEntity object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public GroupEntity findByIdFunc(Long id) {
        
        GroupEntity p = new GroupEntity();
        try
        {
            p =em.find(GroupEntity.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
