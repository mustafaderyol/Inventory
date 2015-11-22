package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.GroupAuthority;
import com.mustafaderyol.inventory.entity.GroupEntity;
import com.mustafaderyol.inventory.idao.IGroupAuthorityDao;
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
public class GroupAuthorityDao implements IGroupAuthorityDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(GroupAuthority object) {
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
    public List<GroupAuthority> allFunc() {
        Query request = em.createNamedQuery("allGroupAuthority");
        return request.getResultList();
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(GroupAuthority.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(GroupAuthority object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public GroupAuthority findByIdFunc(Long id) {
        
        GroupAuthority p = new GroupAuthority();
        try
        {
            p =em.find(GroupAuthority.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }

    @Override
    public List<GroupAuthority> allByGroup(GroupEntity group) {
        Query request = em.createNamedQuery("xGroupAuthority").setParameter("group", group);
        return request.getResultList();
    }
    
}
